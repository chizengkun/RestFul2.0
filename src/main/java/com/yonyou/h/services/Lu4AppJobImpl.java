package com.yonyou.h.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.tempuri.WebServiceSoap;

import com.ufida.g3.domain.dic.ZdYljg;
import com.ufida.hap.context.util.HapSpringContextUtils;
import com.ufida.hap.util.DBAgent;
import com.ufida.hap.util.DateUtils;
import com.yonyou.h.util.C;

public class Lu4AppJobImpl implements Lu4AppJobIntf {

	private LuPhs4AppIntf luphs4AppIntf;

	public LuPhs4AppIntf getLuphs4AppIntf() {
		return luphs4AppIntf;
	}

	@Autowired
	public void setLuphs4AppIntf(LuPhs4AppIntf luphs4AppIntf) {
		this.luphs4AppIntf = luphs4AppIntf;
	}

	private DBAgent getDBAgent() {
		return DBAgent.getInstance();
	}

	private void newTransLog(String sqbm, String empi, Long lday) {
		Map<String,Object> condi = new HashMap<String, Object>();
		condi.put("sqbm", sqbm);
		condi.put("empi", empi);
		condi.put("lday", lday);
		String sql="INSERT INTO APP_UPLOG(SQBM,EMPI,QYRQ) VALUES(:sqbm, :empi, :lday)";
		getDBAgent().executeSQL(sql, condi);
	}

	private void newTransErrorLog(String sqbm, String empi, Long lday, String errmsg) {
		Map<String,Object> condi = new HashMap<String, Object>();
		condi.put("sqbm", sqbm);
		condi.put("empi", empi);
		condi.put("lday", lday);
		condi.put("errmsg", errmsg);
		String sql="INSERT INTO APP_UPERRORLOG(SQBM,EMPI,QYRQ,ERRMSG) VALUES(:sqbm, :empi, :lday,:errmsg)";
		getDBAgent().executeSQL(sql, condi);
	}

	private void getPHR(String sqbm) {
		Map<String, Object> condi = new HashMap<String, Object>();
		condi.put("sqbm", sqbm);
		Long lday = DateUtils.dateToLongDonly(DateUtils.currentDate());
		condi.put("qyrq", lday);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT A0.EMPI FROM DA_GRDA0 A0, DA_GRDA1 A1");
		sb.append(" WHERE A0.SQBM=A1.SQBM AND A0.EMPI=A1.EMPI");
		sb.append(" AND A0.STATE='1' AND A0.SQBM=:sqbm AND A1.QYRQ=:qyrq ");
		sb.append(" AND A0.EMPI NOT IN (SELECT EMPI FROM APP_UPLOG WHERE SQBM=:sqbm AND QYRQ=:qyrq)");
		List<Map<String, Object>> ret = getDBAgent().querySQL(sb.toString(), condi, null);

		if (ret != null && ret.size() > 0) {
			// TODO:�����Ѿ��ϴ��ļ�¼
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement(C.REQUEST_ROOT_NODE);

			for (Map<String, Object> obj : ret) {
				String empi = obj.get("EMPI").toString();
				root.addElement("empi").addText(empi);
				// ȡ���ݷ�װ
				try {
					String req = luphs4AppIntf.getPHS(doc.asXML());
					
					/*Document doc111 = DocumentHelper.parseText( req);
					System.out.println( doc111.asXML());*/
					WebServiceSoap websvr = HapSpringContextUtils.getBean(WebServiceSoap.class);

					Document retdoc = DocumentHelper.parseText(websvr.updateData(req));
					Element ment = (Element) retdoc.selectSingleNode("//resultcode");
					if (ment != null) {
						if (ment.getText().equals("0")) {
							// �ϴ��ɹ���
							newTransLog(sqbm, empi, lday);
						} else {
							// ʧ��
							Element errment = (Element) retdoc.selectSingleNode("//resultmsg");
							if (errment != null) {
								// ����ʧ����Ϣ
								newTransErrorLog(sqbm, empi, lday, errment.getText());
							} else {
								// ��Ϣ�Լ�����
								newTransErrorLog(sqbm, empi, lday, "�ϴ�ʧ��");
							}
						}
					}
				} catch (DocumentException e) {
					e.printStackTrace();
					newTransErrorLog(sqbm, empi, lday, e.getMessage());
					// ��¼��������־�У��������
				}
			}
		}
	}

	private void getJobs() {
		// ���޺����翵�ύ����
		Map<String, Object> condi = new HashMap<String, Object>();
		condi.put("bm[like]$v%", "440303");
		condi.put("sendstate", 1L);
		List<ZdYljg> yljgList = getDBAgent().find(ZdYljg.class, condi, null);
		for (ZdYljg yljg : yljgList) {
			// ��������ȡ��Ӧ������
			getPHR(yljg.getBm());
		}
	}

	// �ϴ�����
	public void transJob() {
		getJobs();
	}

	public void actionCheck() {
		//

	}

}
