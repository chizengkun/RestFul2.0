/**
 * $Author: gaohanga $
 * $Rev: 2329 $
 * $Date:: 2010-03-15 13:43:01#$:
 *
 * Copyright (c) 2010 UFIDA, Inc. All rights reserved.
 *
 * This software is the proprietary information of UFIDA, Inc.
 * Use is subject to license terms.
 */
package com.yonyou.h.services;

/**
 * <p>
 * Title: UFIDA G3
 * </p>
 * <p>
 * Description: ���ݿ��������������
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: ����ҽ��������Ϣϵͳ���޹�˾
 * </p>
 */
public interface DbaHelpService {
	public Long selectSysID(long lx);

	public Long[] selectSysIDs(long lx, Integer count);

	public Long[] selectSysIDWithTime(long lx, String RestRq, String sqbm);

}
