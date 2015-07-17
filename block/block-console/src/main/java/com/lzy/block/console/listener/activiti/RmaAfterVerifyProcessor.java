package com.lzy.block.console.listener.activiti;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RmaAfterVerifyProcessor implements TaskListener {
 
	private static final long serialVersionUID = 2069864479566575371L;
	
	private static Logger logger = Logger.getLogger(RmaAfterVerifyProcessor.class.getName());

	public void notify(DelegateTask delegateTask) {
		
		logger.info("退换货审核-----------");
		String checkState = (String) delegateTask.getVariable("checkState");
		String checkDesc = (String) delegateTask.getVariable("checkDesc");
		String rmaNo = (String) delegateTask.getVariable("rmaNo");
		logger.info(rmaNo+"-----------"+checkState+"----"+checkDesc);
//		RmaModel rmaModel = rmaService.getByRmaNo(rmaNo);
//        boolean result = rmaService.checkRma(rmaModel.getRmaId(), checkDesc, checkState);
//        if(result){
//        	logger.info("退换货审核通过-----------");
//        	RmaDetailModel rmaDetailModel=new RmaDetailModel();
//        	rmaDetailModel.setRmaNo(rmaModel.getRmaNo());
//        	List<RmaDetailModel> details=new ArrayList<RmaDetailModel>();
//        	try {
//        		details=rmaDetailService.getAll(rmaDetailModel);
//			} catch (Exception e) {
//				logger.info("获取退换货明细错误-----------"+e.getMessage());
//	        	throw new RuntimeException();
//			}
//        	rmaModel.setDetails(details);
//        	AddRmaNoticeVo addRmaNoticeVo=convertRmaVoToAddRmaNVo(rmaModel);
//        	logger.info(addRmaNoticeVo.toString());
//        	if(RmaUtil.validateRmaInfo(addRmaNoticeVo)){
//        		if(RmaUtil.validateDetails(addRmaNoticeVo.getProductDetail())){
//        			logger.info("校验退货信息成功!");
//        			rabbitTemplate.convertAndSend("queues.addRma.notice",addRmaNoticeVo);//通知wms新增退换货
//        		}else{
//        			logger.error("验证退货详情失败!");
//        		}
//        	}else{
//        		logger.error("验证退货主信息失败!");
//        	}
//    		
//        }else{
//        	logger.info("退换货审核不通过-----------");
//        	throw new RuntimeException();
//        }
//         
	}
	
	
 
}
