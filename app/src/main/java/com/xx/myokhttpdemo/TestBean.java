package com.xx.myokhttpdemo;

import com.xx.myokhttpdemo.utils.RBResponse;

import java.util.List;

/**
 * Created by XuXiang on 2016/11/8.
 */
public class TestBean extends RBResponse {
    /**
     * taskId : AQS49494894891
     * isOnTimeFinish : 未按时
     * taskHis : 验收通过
     * userName : 张1
     * taskTimeLimit : 5小时
     * taskPerson : 王五
     * firBeginTime : 2015-12-03 15:28:02
     * firEndTime : 2015-11-02 15:15:02
     * saveUseTime : 6小时
     * taskFinishStau : 按时执行
     * saveBeginTime : 2015-12-03 15:28:02
     * saveBeginAd : 北京海淀
     * saveFinishTime : 2015-12-03 15:28:02
     * saveFinishAd : 北京昌平
     * carId : 京A45896
     * belongAd : 清水河
     * baseInf_saveDes : 无信号
     * baseInf_payType : 司机付费
     * repairTellPerson : 王大春
     * repairTellTime : 2015-12-03 15:28:02
     * reason : GPS老化
     * nowReasonAna : GPS信号差
     * repairInf_saveDes : 更换GPS
     * repairInf_payType : 司机付
     * delayDes : 无延误
     * recodeId : 1546
     * oldProductId : 166
     * oldTellId : 1156
     * newProductId : 5660
     * newTellId : 666
     * repairPerson : adad
     * repairTime : 2015-12-03 15:28:02
     * acceptAdvice : 未按规定完成
     * acceptTime : 2015-12-03 15:28:02
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String taskId;
        private String isOnTimeFinish;
        private String taskHis;
        private String userName;
        private String taskTimeLimit;
        private String taskPerson;
        private String firBeginTime;
        private String firEndTime;
        private String saveUseTime;
        private String taskFinishStau;
        private String saveBeginTime;
        private String saveBeginAd;
        private String saveFinishTime;
        private String saveFinishAd;
        private String carId;
        private String belongAd;
        private String baseInf_saveDes;
        private String baseInf_payType;
        private String repairTellPerson;
        private String repairTellTime;
        private String reason;
        private String nowReasonAna;
        private String repairInf_saveDes;
        private String repairInf_payType;
        private String delayDes;
        private String recodeId;
        private String oldProductId;
        private String oldTellId;
        private String newProductId;
        private String newTellId;
        private String repairPerson;
        private String repairTime;
        private String acceptAdvice;
        private String acceptTime;

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getIsOnTimeFinish() {
            return isOnTimeFinish;
        }

        public void setIsOnTimeFinish(String isOnTimeFinish) {
            this.isOnTimeFinish = isOnTimeFinish;
        }

        public String getTaskHis() {
            return taskHis;
        }

        public void setTaskHis(String taskHis) {
            this.taskHis = taskHis;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getTaskTimeLimit() {
            return taskTimeLimit;
        }

        public void setTaskTimeLimit(String taskTimeLimit) {
            this.taskTimeLimit = taskTimeLimit;
        }

        public String getTaskPerson() {
            return taskPerson;
        }

        public void setTaskPerson(String taskPerson) {
            this.taskPerson = taskPerson;
        }

        public String getFirBeginTime() {
            return firBeginTime;
        }

        public void setFirBeginTime(String firBeginTime) {
            this.firBeginTime = firBeginTime;
        }

        public String getFirEndTime() {
            return firEndTime;
        }

        public void setFirEndTime(String firEndTime) {
            this.firEndTime = firEndTime;
        }

        public String getSaveUseTime() {
            return saveUseTime;
        }

        public void setSaveUseTime(String saveUseTime) {
            this.saveUseTime = saveUseTime;
        }

        public String getTaskFinishStau() {
            return taskFinishStau;
        }

        public void setTaskFinishStau(String taskFinishStau) {
            this.taskFinishStau = taskFinishStau;
        }

        public String getSaveBeginTime() {
            return saveBeginTime;
        }

        public void setSaveBeginTime(String saveBeginTime) {
            this.saveBeginTime = saveBeginTime;
        }

        public String getSaveBeginAd() {
            return saveBeginAd;
        }

        public void setSaveBeginAd(String saveBeginAd) {
            this.saveBeginAd = saveBeginAd;
        }

        public String getSaveFinishTime() {
            return saveFinishTime;
        }

        public void setSaveFinishTime(String saveFinishTime) {
            this.saveFinishTime = saveFinishTime;
        }

        public String getSaveFinishAd() {
            return saveFinishAd;
        }

        public void setSaveFinishAd(String saveFinishAd) {
            this.saveFinishAd = saveFinishAd;
        }

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public String getBelongAd() {
            return belongAd;
        }

        public void setBelongAd(String belongAd) {
            this.belongAd = belongAd;
        }

        public String getBaseInf_saveDes() {
            return baseInf_saveDes;
        }

        public void setBaseInf_saveDes(String baseInf_saveDes) {
            this.baseInf_saveDes = baseInf_saveDes;
        }

        public String getBaseInf_payType() {
            return baseInf_payType;
        }

        public void setBaseInf_payType(String baseInf_payType) {
            this.baseInf_payType = baseInf_payType;
        }

        public String getRepairTellPerson() {
            return repairTellPerson;
        }

        public void setRepairTellPerson(String repairTellPerson) {
            this.repairTellPerson = repairTellPerson;
        }

        public String getRepairTellTime() {
            return repairTellTime;
        }

        public void setRepairTellTime(String repairTellTime) {
            this.repairTellTime = repairTellTime;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getNowReasonAna() {
            return nowReasonAna;
        }

        public void setNowReasonAna(String nowReasonAna) {
            this.nowReasonAna = nowReasonAna;
        }

        public String getRepairInf_saveDes() {
            return repairInf_saveDes;
        }

        public void setRepairInf_saveDes(String repairInf_saveDes) {
            this.repairInf_saveDes = repairInf_saveDes;
        }

        public String getRepairInf_payType() {
            return repairInf_payType;
        }

        public void setRepairInf_payType(String repairInf_payType) {
            this.repairInf_payType = repairInf_payType;
        }

        public String getDelayDes() {
            return delayDes;
        }

        public void setDelayDes(String delayDes) {
            this.delayDes = delayDes;
        }

        public String getRecodeId() {
            return recodeId;
        }

        public void setRecodeId(String recodeId) {
            this.recodeId = recodeId;
        }

        public String getOldProductId() {
            return oldProductId;
        }

        public void setOldProductId(String oldProductId) {
            this.oldProductId = oldProductId;
        }

        public String getOldTellId() {
            return oldTellId;
        }

        public void setOldTellId(String oldTellId) {
            this.oldTellId = oldTellId;
        }

        public String getNewProductId() {
            return newProductId;
        }

        public void setNewProductId(String newProductId) {
            this.newProductId = newProductId;
        }

        public String getNewTellId() {
            return newTellId;
        }

        public void setNewTellId(String newTellId) {
            this.newTellId = newTellId;
        }

        public String getRepairPerson() {
            return repairPerson;
        }

        public void setRepairPerson(String repairPerson) {
            this.repairPerson = repairPerson;
        }

        public String getRepairTime() {
            return repairTime;
        }

        public void setRepairTime(String repairTime) {
            this.repairTime = repairTime;
        }

        public String getAcceptAdvice() {
            return acceptAdvice;
        }

        public void setAcceptAdvice(String acceptAdvice) {
            this.acceptAdvice = acceptAdvice;
        }

        public String getAcceptTime() {
            return acceptTime;
        }

        public void setAcceptTime(String acceptTime) {
            this.acceptTime = acceptTime;
        }
    }
}
