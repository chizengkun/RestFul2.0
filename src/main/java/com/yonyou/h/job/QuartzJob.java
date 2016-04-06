package com.yonyou.h.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.yonyou.h.services.Lu4AppJobIntf;

public class QuartzJob {

	private static boolean bdoing = false;
	private Lu4AppJobIntf lu4AppJobIntf;

	// ʵ�����ݵ��ϴ�
	public Lu4AppJobIntf getLu4AppJobIntf() {
		return lu4AppJobIntf;
	}

	@Autowired
	public void setLu4AppJobIntf(Lu4AppJobIntf lu4AppJobIntf) {
		this.lu4AppJobIntf = lu4AppJobIntf;
	}

	public void work() {
		System.out.println("quartz run work!");
	}

	public void dojob() {
		if (!bdoing) {
			// ����δ���ʱ�ٴ����Ĵ�����ʱ�ñ�ʶ��¼
			System.out.println("quartz dojob!");
			/*
			 * SimpleDateFormat df = new
			 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			 * System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
			 */
			bdoing = true;
			try {
				lu4AppJobIntf.transJob();
			} finally {
				bdoing = false;
			}
		} else {
			System.out.println("quartz doing");
		}
	}
}
