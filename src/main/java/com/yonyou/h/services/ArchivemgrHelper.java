/**
 * $Author: liyjb $
 * $Rev: 2988 $
 * $Date:: 2010-04-14 16:44:13 +#$:
 *
 * Copyright (c) 2010 UFIDA, Inc. All rights reserved.
 *
 * This software is the proprietary information of UFIDA, Inc.
 * Use is subject to license terms.
 */
package com.yonyou.h.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;

import com.ufida.g3.domain.da.PoDaDaGrda0;
import com.ufida.g3.domain.da.PoDaDaGrda1;
import com.ufida.g3.domain.da.PoZxdaJtda;
import com.ufida.g3.domain.region.WsxxBa;
import com.ufida.hap.core.exception.AppException;
import com.ufida.hap.util.ConvertUtils;
import com.ufida.hap.util.DBAgent;
import com.ufida.hap.util.FlipInfo;
import com.ufida.hap.util.ParamUtil;
import com.ufida.hap.util.StringUtils;

public class ArchivemgrHelper {

  

    // ���������в����Ƿ������Ӧ����Ա������Ϣ������ڣ����ض�Ӧ��Ψһ��ʶ

    public static WsxxBa getWsxxDaByEmpi(Long EMPI, DBAgent dba){
    	return dba.findByPK(WsxxBa.class, EMPI);
    }
    
    public static List<WsxxBa> getWsxxDa(PoDaDaGrda0 grda, DBAgent dba) {
        // DONE �������Ѵ����浽WSXX_BA��������ȷ������Ӧ�����Ȳ�ѯ���������ٽ��д���
        Map<String, Object> condi = new HashMap<String, Object>();
        List<WsxxBa> rt = null;

        if ((grda.getXm() != null && grda.getXm() != null && grda.getCsrq() != null) || grda.getSfzh() != null
                || grda.getSbkh() != null || grda.getSbbh() != null || grda.getJkkh() !=null) {

            // �����������ͨ�����֤���籣���Ž����жϣ����������ʱ��ͨ���������������ڡ��Ա�����ж��Ƿ���ڴ���
            if ((grda.getSfzh() != null) || (grda.getSbbh() != null) || (grda.getSbkh() != null) || grda.getJkkh() !=null) {           
            	if (!StringUtils.checkNullOrEmpty( grda.getSfzh()))
                    condi.put("sfzh", grda.getSfzh());
                if (!StringUtils.checkNullOrEmpty( grda.getSbbh()))
                    condi.put("sbh", grda.getSbbh());
                if (!StringUtils.checkNullOrEmpty(grda.getSbkh()))
                    condi.put("sbkh", grda.getSbkh());
                if (!StringUtils.checkNullOrEmpty( grda.getJkkh()))
                    condi.put("jkkh", grda.getJkkh());
                CommOperHelper.applyPersonIdxCondi(condi, CommOperHelper.PTT_WSXXBA, null, "empi");
            } else {
            	//û��Ψһ��ʶֱ���½�
                return  new ArrayList<WsxxBa>();
//                condi.put("k1", Restrictions.eq("xm", grda.getXm()));
//                condi.put("k2", Restrictions.eq("xb", grda.getXb()));
//                condi.put("k3", Restrictions.eq("csrq", grda.getCsrq()));
            }
            FlipInfo fi = new FlipInfo();
            fi.setSize(1);
            fi.setNeedTotal(false);
            rt = dba.find(WsxxBa.class, condi, fi);//����ֻ����һ��
        } else
            rt = new ArrayList<WsxxBa>();
        // �������д��ڸõ�����ֱ����������EMPI(�ڽ��ŵ���Ҫע�⣬�����EMPIΪ�ַ���ʱ���ܻ���ڴ���)
        return rt;

    }

    /**
     * �������ݴӸ��˵�����������Ϣ����
     * @param wsba
     * @param grda
     * @param hklb
     * @return
     */
    public static WsxxBa PoDaDaGrda0ToWsxxBa(WsxxBa wsba, PoDaDaGrda0 grda, String hklb) {
        String xb;
        if ("2".equals(grda.getXb())) {
            xb = "Ů";
        } else {
            xb = "��";
        }
        // ͬʱ��������������
        if (wsba == null) {
            wsba = new WsxxBa();
            wsba.setEmpi(grda.getEmpi());
            wsba.setSfzlb(1L);
        }
        wsba.setXm(grda.getXm());
        wsba.setXb(xb);
        wsba.setCsrq(grda.getCsrq());
        wsba.setSfzh(grda.getSfzh());
        wsba.setJkkh(grda.getJkkh());
        wsba.setSbh(grda.getSbbh());
        wsba.setSbkh(grda.getSbkh());
        wsba.setDah(grda.getDah());
        if (hklb != null)
        	wsba.setHklb(ConvertUtils.toLong(hklb));
        /*else {
             // �������е���Ϣ�Ե�ǰ������ϢΪ׼�����в����ϵģ������������е���(��ǰ�����в�Ϊ�յ���Ÿ���)
             // �������е���Ϣ���ܻ�ȸ��˵������е�ȫ��һЩ
             if ((wsba.getXm() == null)
                     || ((!wsba.getXm().equalsIgnoreCase(grda.getXm())) && (grda
                             .getXm() != null))) {
                 wsba.setXm(grda.getXm());
             }
             if ((wsba.getXb() == null)
                     || ((!wsba.getXb().equalsIgnoreCase(xb)) && (grda.getXb() != null))) {
                 wsba.setXb(xb);
             }
             if ((wsba.getCsrq() == null)
                     || ((!wsba.getCsrq().equals(grda.getCsrq())) && (grda
                             .getCsrq() != null))) {
                 wsba.setCsrq(grda.getCsrq());
             }
             if ((wsba.getSfzh() == null)
                     || ((!wsba.getSfzh().equalsIgnoreCase(grda.getSfzh())) && (grda
                             .getSfzh() != null))) {
                 wsba.setSfzh(grda.getSfzh());
             }
             if ((wsba.getSbh() == null)
                     || ((!wsba.getSbh().equalsIgnoreCase(grda.getSbbh())) && (grda
                             .getSbbh() != null))) {
                 wsba.setSbh(grda.getSbbh());
             }
             if ((wsba.getSbkh() == null)
                     || ((!wsba.getSbkh().equalsIgnoreCase(grda.getSbkh())) && (grda
                             .getSbkh() != null))) {
                 wsba.setSbkh(grda.getSbkh());
             }
             if ((wsba.getHklb() == null)
                     || ((hklb != null) && (!wsba.getHklb().equals(hklb)))) {
                 wsba.setHklb(ConvertUtils.toLong(hklb));
             }
         }*/
        return wsba;
    }

    @SuppressWarnings("unchecked")
    public static void saveJTDA(PoDaDaGrda0 grda0, PoDaDaGrda1 grda1, DBAgent dba, DbaHelpService dhs) {
        Map condi = new HashMap();
        condi.put("hzid", grda0.getEmpi());

        PoZxdaJtda jtda = (PoZxdaJtda) dba.findUniqueResult(PoZxdaJtda.class, condi);
        if (jtda != null) {
            grda0.setJtdaid(jtda.getJtid());
            jtda.setJtdz(grda1.getJtdz());
            jtda.setJtdz1(grda1.getJtdz1());
            jtda.setJtdz2(grda1.getJtdz2());
            jtda.setJtdz3(grda1.getJtdz3());
            jtda.setJtdz4(grda1.getJtdz4());
            jtda.setJtdz5(grda1.getJtdz5());
            jtda.setJtdz6(grda1.getJtdz6());
            jtda.setJtdz7(grda1.getJtdz7());
            if (jtda.getJtrs()==null){
            	jtda.setJtrs(1L);
            }
            else{
            	jtda.setJtrs(jtda.getJtrs() + 1L);
            }
            
            dba.update(null, jtda);
        } else {
            // �����ͥ����, ���¸��˵���IDΪ��ͥ����ID
            jtda = new PoZxdaJtda();
            jtda.setHzid(grda1.getEmpi());
            jtda.setHklb(grda1.getHklb());
            jtda.setSqbm(grda0.getSqbm());
            jtda.setJtyb(grda1.getJtyb());
            jtda.setHzxm(grda0.getXm());
            jtda.setJtdh(grda1.getJtdh());
            jtda.setHklb(grda1.getHklb());
            jtda.setJtdz(grda1.getJtdz());
            jtda.setJtdz1(grda1.getJtdz1());
            jtda.setJtdz2(grda1.getJtdz2());
            jtda.setJtdz3(grda1.getJtdz3());
            jtda.setJtdz4(grda1.getJtdz4());
            jtda.setJtdz5(grda1.getJtdz5());
            jtda.setJtdz6(grda1.getJtdz6());
            jtda.setJtdz7(grda1.getJtdz7());
            jtda.setJdrq(grda0.getJdrq());
            jtda.setJdsq(grda0.getJdsq());
            jtda.setJdrymc(grda0.getJdrymc());
            jtda.setJtrs(1L);
            jtda.setJtbh(dhs.selectSysID(1009L).toString());
            Long jtdaid = dhs.selectSysID(1007l);
            jtda.setJtid(jtdaid);
            grda0.setJtdaid(jtdaid);
            dba.save(null, jtda);
        }

    }

}
