/**
 * 
 */
package hyj.renbao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.alibaba.fastjson.JSON;
import com.ebtins.dto.open.CarLicenseInfoVo;
import com.ebtins.dto.open.CarModelInfoVo;
import com.ebtins.dto.open.CarOrderRelationInfoVo;
import com.ebtins.dto.open.CarOwnerInfoVo;
import com.ebtins.dto.open.CarQuoteInfoVo;
import com.ebtins.dto.open.CarQuoteInsItemVo;
import com.ebtins.dto.open.CarQuoteReq;
import com.ebtins.dto.open.CarQuoteRes;
import com.ebtins.dto.open.CarRenewalRes;
import com.ebtins.open.common.constant.CarInsuranceConstant;
import com.ebtins.open.common.util.StringUtil;
import com.ebtins.open.common.util.ValidatorUtil;

import ebtins.smart.proxy.company.huaan.dto.ResContent;
import ebtins.smart.proxy.company.huaan.util.HuaanUtil;
import ebtins.smart.proxy.company.renbao.dto.CiCarShipTax;
import ebtins.smart.proxy.company.renbao.dto.CiInsureDemand;
import ebtins.smart.proxy.company.renbao.dto.CiInsureTax;
import ebtins.smart.proxy.company.renbao.dto.PrpCitemKind;
import ebtins.smart.proxy.company.renbao.dto.RenbaoBiInsure;
import ebtins.smart.proxy.company.renbao.dto.RenbaoQuoteContent;
import ebtins.smart.proxy.company.renbao.dto.QuoteSave.QueryPayForContent;
import ebtins.smart.proxy.company.renbao.dto.QuoteSave.RefreshPlanContent;
import ebtins.smart.proxy.company.renbao.service.RenbaoQuoteSaveService;
import ebtins.smart.proxy.company.renbao.service.RenbaoQuoteService;
import ebtins.smart.proxy.company.renbao.util.RenbaoClientSSLUtil;
import ebtins.smart.proxy.company.renbao.util.RenbaoConfig;
import ebtins.smart.proxy.company.renbao.util.RenbaoUtil;
import ebtins.smart.proxy.conf.Constants;
import hyj.login.RenBaoLoginA;

/**
 * @ClassName: Quote
 * @Description: TODO()
 * @author yejie.huang
 * @date 2016年11月21日 下午3:59:26
 *
 */

public class Quote {
	/**
	 * 
	 * @Description: TODO(执行思路：1、保费计算;2、保存。)
	 * @param req
	 * @author yejie.huang
	 * @date 2016年11月29日 下午4:32:14
	 */
	/*public Map<String,String> getQuote(CarQuoteReq req) throws Exception{
		 String prepareEditReferer = "http://10.134.130.208:8000/prpall/menu/showMenu.do?systemCode=prpall&userCode=99355911";
		 String quoteReferer = "http://10.134.130.208:8000/prpall/business/prepareEdit.do?bizType=PROPOSAL&editType=NEW";
		 String cookie = RenBaoLoginA.login();
		 //String prepareEditBody = RenBaoLoginA.get(quoteReferer, "GBK", cookie, prepareEditReferer, null);
		 //Document doc = Jsoup.parse(prepareEditBody);
		 //String randomProposalNo = doc.getElementById("randomProposalNo").val();
		 String body = "{'totalRecords':1,'data':[{'ciDemandVos':[],'biInsuredemandVoList':[{'prpCfixations':[{'realProfits':0,'operationInfo':'1','riskClass':'A','riskPremium':100,'taxorAppend':5.6,'flag':'','discount':95,'profits':5,'cost':4.31,'payMentR':38,'basePremium':100,'basePayMentR':30.44,'costRate':35,'operateTimeForHis':null,'signPremium':100,'prpCmain':null,'responseCode':'','poundAge':16.69,'id':{'proposalNo':'','riskCode':'DAA'},'riskSumPremium':0,'profitClass':'','isQuotation':'','remark':'','realDisCount':0,'realPayMentR':0,'insertTimeForHis':null,'errorMessage':''}],'ciInsureDemandDAA':{'policyadjustvalue':0,'tonCount':0,'frameNo':'','lastpolicyexpiredate':null,'demandNo':'V0101PICC440316001480313726689','demandeffectendtime':null,'insurerArea':'','preferentialDay':0,'commissionRate':0,'resureFundFee':0,'previousPay':0,'lastpolicyquerydate':null,'extendChar1':'','proposalNo':'','responseRemark':null,'premium':0,'operatorCode':'','enrollDate':null,'districtCoeff':0,'chgVehicleMessage':'','ciCommissionMessage':{'ciCertificates':[{'certificateValidDate':{'date':19,'day':1,'timezoneOffset':-480,'year':103,'month':4,'hours':0,'seconds':0,'minutes':0,'time':1053273600000},'certificateId':{'certificateId':0,'demandNo':'V0101PICC440316001480313726689'},'ciCommissionMessage':null,'certificateExpireDate':{'date':18,'day':3,'timezoneOffset':-480,'year':116,'month':4,'hours':0,'seconds':0,'minutes':0,'time':1463500800000}},{'certificateValidDate':{'date':28,'day':4,'timezoneOffset':-480,'year':116,'month':3,'hours':0,'seconds':0,'minutes':0,'time':1461772800000},'certificateId':{'certificateId':1,'demandNo':'V0101PICC440316001480313726689'},'ciCommissionMessage':null,'certificateExpireDate':{'date':17,'day':5,'timezoneOffset':-480,'year':119,'month':4,'hours':0,'seconds':0,'minutes':0,'time':1558022400000}}],'newVehicleEffectReason':'','commissionRateUpper':1,'individualProducerCode':'440321100061','practiceCertificateCode':'','demandNo':'V0101PICC440316001480313726689','groupCompany':'','producerEffectReason':'','ciInsureDemandContracts':[{'operateTimeForHis':null,'ciCommissionMessage':null,'contractExpireDate':{'date':18,'day':3,'timezoneOffset':-480,'year':116,'month':4,'hours':0,'seconds':0,'minutes':0,'time':1463500800000},'contractValidDate':{'date':21,'day':2,'timezoneOffset':-480,'year':113,'month':4,'hours':0,'seconds':0,'minutes':0,'time':1369065600000},'proposalno':'','insertTimeForHis':null,'contractno':'440321112020600','id':{'serialno':1,'demandNo':'V0101PICC440316001480313726689'}},{'operateTimeForHis':null,'ciCommissionMessage':null,'contractExpireDate':{'date':30,'day':3,'timezoneOffset':-480,'year':115,'month':8,'hours':0,'seconds':0,'minutes':0,'time':1443542400000},'contractValidDate':{'date':2,'day':1,'timezoneOffset':-480,'year':114,'month':5,'hours':0,'seconds':0,'minutes':0,'time':1401638400000},'proposalno':'','insertTimeForHis':null,'contractno':'440321114061200','id':{'serialno':2,'demandNo':'V0101PICC440316001480313726689'}},{'operateTimeForHis':null,'ciCommissionMessage':null,'contractExpireDate':{'date':17,'day':5,'timezoneOffset':-480,'year':119,'month':4,'hours':0,'seconds':0,'minutes':0,'time':1558022400000},'contractValidDate':{'date':19,'day':0,'timezoneOffset':-480,'year':113,'month':4,'hours':0,'seconds':0,'minutes':0,'time':1368892800000},'proposalno':'','insertTimeForHis':null,'contractno':'440321116051900','id':{'serialno':3,'demandNo':'V0101PICC440316001480313726689'}}],'accountNumber':4000021219200320544,'accountName':'深圳市宜保通保险销售有限公司','ciInsureDemand':null,'bankCode':'工商银行','bankName':'中国工商银行深圳市红围支行','agentTypeCode':'3','operateTimeForHis':null,'certificateNo':'203131000000800','individualProducerName':'深圳市宜保通保险销售有限公司','claimEffectReason':'','insertTimeForHis':null,'companyCommissionRateUpper':1},'ciRiskWarningClaimItems':[],'reinsureFlag':'','usbkey':'','carOwner':'','ciCoveragePremiums':[{'pureRiskPremium':1361.59556,'pureRiskPremiumFlag':'1','id':{'coverageCode':'0101200','queryNo':'V0101PICC440316001480313726689','modelCode':'BJHASXUB0002'}}],'rescueFundRate':0,'lastproducercode':'','disPlacement':'','posNo':'','lastpolicybilldate':null,'carStatus':'','flag':'','ineffectualDate':null,'dummyresponseremark':'','rateRloatFlag':'','lastEffectiveDate':null,'certificateDate':null,'licenseColorCode':'','demandTime':{'date':28,'day':1,'timezoneOffset':-480,'year':116,'month':10,'hours':14,'seconds':27,'minutes':15,'time':1480313727076},'lastpolicytotalpremium':null,'exhaustCapacity':0,'carKindCode':'','amount':0,'taxFlag':'','brandName':'','computerip':'','preferentialPremium':0,'adjustEnd':null,'remark':'','restricFlag':'','preferentialFormula':'','requestRemark':null,'benchMarkPremium':0,'seatCount':0,'peccancyAdjustReason':'','pmVehicleMessage':'','coverageCode':'','prevalidno':'','busilastyearstartdate':null,'driverRateReason':'','busiInsurerArea':'','manufacturerName':'','lastyearenddate':null,'validCheckDate':null,'ipPart':'','coverageType':'','transferDate':null,'engineNo':'','adjustStart':null,'driverCoeff':0,'dummyrequestremark':'','areaCode':'','netPremium':0,'endValidDate':null,'useNatureCode':'','policyNo':'','lastExpireDate':null,'peccancyCoeff':0,'busiLastYearEndDate':null,'licenseNo':'','comCode':'44030716','taxActual':0,'proconfirmenddate':'','vehicleCategory':'','modelCode':'','insertTimeForHis':null,'ciLastPolicyInfo':null,'taxPremium':0,'startDate':null,'lastBillDate':null,'lateFee':0,'salePrice':'','haulage':'','taxTotal':'','fuelType':'','makeDate':null,'pmUserType':'','basePremium':0,'operateTimeForHis':null,'useTypeSource':'','claimCoeff':0,'wholeWeight':'','taxRate':0,'useTypeMessage':'','ownerName':'','licenseType':'','claimAdjustReason':'','checkDate':null,'lastpolicyeffectivedate':null,'channeltype':'','lastyearstartdate':null,'querypastdate':null,'endDate':null,'vehicleOwnerMessageType':'','brandCName':'','dzflag':'','noVehicleMessageType':'','districtRateReason':'','proconfirmstartdate':''},'ciInsureDemandRisk':null,'errMessage':'','prpCitemCars':[{'tonCount':0,'endSiteName':'','frameNo':'','licenseFlag':'','useYears':0,'salesNumber':'','insuredTypeCode':'','isCriterion':0,'modelCodeAlias':'','fullendor':'','prpCcarModels':[],'enrollDate':null,'operationArea':'','hkLicenseNo':'','prpCtrafficDetails':[],'groupEndDate':'','versionNo':'','carProofNo':'','id':null,'monopolyCode':'','exhaustScale':0,'businessClassCode':'','isDropinVisitInsure':0,'carId':'','transferVehicleFlag':'','monopolyFlag':'','carCustType':'','flag':'','runAreaCode':'','vehicleBrand':'','countryCode':'','riskCode':'','prpCmain':null,'salesPhone':'','driverType':'','licenseColorCode':'','carLoanFlag':'','startSiteName':'','lossRatio':'','carKindCode':'','carDealerName':'','brandName':'','invoiceNo':'','isRemote':'','runMiles':0,'clauseType':'','remark':'','seatCount':0,'prpCcarDevices':[],'licenseNo1':'','loanVehicleFlag':'','transferDate':null,'engineNo':'','newCarFlag':'','carDealerCode':'','licenseNo3':'','licenseNo2':'','hkFlag':'','vehicleTypeDescription':'','useNatureCode':'','groupStartDate':'','coefficient2':1,'licenseNo':'','coefficient1':1,'coefficient3':0.1000000000000000055511151231257827021181583404541015625,'currency':'','carCounts':0,'rateCode':'','isSilageHarvester':'','actualValue':0,'vehicleCategory':'','monopolyName':'','modelCode':'','insertTimeForHis':null,'carProofDate':null,'purchasePrice':0,'colorCode':'','certifiCateDate':null,'vehicleType':'','fuelType':'','makeDate':null,'aliasName':'','carProofType':'','prpCitemCarExts':[],'modelDemandNo':'','safeDevice':'','runAreaName':'','vinNo':'','operateTimeForHis':null,'cylinderCount':0,'noDamageYears':'','energyType':'','licenseType':'','rationName':'','discountType':'','countryNature':'','noNlocalFlag':'','groupCode':'','carUsage':'','salesName':'','otherNature':'','carInsuredRelation':'','carLotEquQuality':0}],'ciEndorDemandLossListBI':[],'prpCprofitFactors':[{'prpCprofitFactorFixes':[],'profitName':'无赔款优待及上年赔款记录','chooseFlag':'1','flag':'','rate':100,'id':{'profitCode':'C01','proposalNo':'','conditionCode':'C0104','serialNo':0},'upperRate':130,'lowerRate':100,'operateTimeForHis':null,'riskCode':'DAA','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'prpCmain':null},{'prpCprofitFactorFixes':[],'profitName':'自主渠道系数','chooseFlag':'1','flag':'','rate':90,'id':{'profitCode':'C02','proposalNo':'','conditionCode':'C0206','serialNo':0},'upperRate':125,'lowerRate':75,'operateTimeForHis':null,'riskCode':'DAA','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'prpCmain':null},{'prpCprofitFactorFixes':[],'profitName':'自主核保优惠系数','chooseFlag':'1','flag':'','rate':105.555556,'id':{'profitCode':'C03','proposalNo':'','conditionCode':'C03','serialNo':0},'upperRate':125,'lowerRate':75,'operateTimeForHis':null,'riskCode':'DAA','condition':'自主核保优惠系数','insertTimeForHis':null,'prpCmain':null}],'ciInsureRiskItem':{'fleetadjustvalue':0,'managementAdjustLower':0,'noClaimRecordReason':'','travelAdjustValue':0,'lossAdjustValue':0,'claimAmountValue':0,'claimAdjustValue':0,'demandNo':'V0101PICC440316001480313726689','peccancyRecordValue':0,'noKindAdjustReason':'','driverAdjustReason':'','kindAdjustValue':0,'proposalNo':'','claimNoAdjustReason':'11','operateTimeForHis':null,'insureInDoorReason':'','noPeccancyAdjustReason':'','insureInDoorValue':0,'claimRecordReason':'','adjustValueReason':'','experienceAdjustUpper':0,'driverAdjustValue':0,'vehicleModelAdjustLower':0,'managementAdjustUpper':0,'theftAdjustValue':0,'lossadjustreason':'','noClaimAmountReason':'','claimAdjustReason':'B31','travelAdjustReason':'','policyNo':'','safeAdjust':0,'loyaltyAdjustValue':0,'vehicleModelAdjustUpper':0,'mileageAdjustValue':0,'noPeccancyRecordReason':'','peccancyAdjustValue':0,'theftAdjustReason':'','loyalltyAdjustReason':'','kindAdjustReason':'','claimRecordValue':0,'experienceAdjustLower':0,'peccancyRecordReason':'','claimAmountReason':'','insertTimeForHis':null,'isContinuouspPolicy':'','mileageAdjustReason':'','peccancyAdjustReason':'','loyaltyAdjustReason':''},'key1':'','ciInsureDemandPayListBI':[],'ciEndorDemandPayListBI':[],'prpCreplenishFactors':[],'lossMessage':'','ciInsureDemandLossListBI':[],'ciRiskWarningClaimItems':[],'ciInsureDemandCheckVo':{'checkQuestion':'','validCheckDate':'','demandNo':'','riskCode':'','checkCode':'','errMessage':'','renewalFlag':'','flag':'','checkAnswer':''},'prpCitemKinds':[{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':1,'serialNo':0},'kindCode':'050202','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':1,'serialNo':0},'kindCode':'050202','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':1,'serialNo':0},'kindCode':'050202','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':1},'currency':'CNY','kindCode':'050202','operateTimeForHis':null,'totalProfit':104.74,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'Y','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':1361.59556,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':1990.02,'currency1':'CNY','currency2':'','netPremium':1877.38,'modeCode':'','startHour':0,'kindName':'车辆损失险','deductibleRate':0,'rate':0,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':1},'currency':'CNY','kindCode':'050202','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':112.64,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00010 000','basePremium':0,'operateTimeForHis':null,'unitAmount':0,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050051','quantity':0,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':102800,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':2094.76},{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':3,'serialNo':0},'kindCode':'050501','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':3,'serialNo':0},'kindCode':'050501','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':3,'serialNo':0},'kindCode':'050501','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':3},'currency':'CNY','kindCode':'050501','operateTimeForHis':null,'totalProfit':32.73,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'N','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':621.83,'currency1':'CNY','currency2':'','netPremium':586.63,'modeCode':'','startHour':0,'kindName':'全车盗抢险','deductibleRate':0,'rate':0.338,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':3},'currency':'CNY','kindCode':'050501','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':35.2,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00010 000','basePremium':78,'operateTimeForHis':null,'unitAmount':0,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050054','quantity':0,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':102800,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':654.56},{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':5,'serialNo':0},'kindCode':'050602','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':5,'serialNo':0},'kindCode':'050602','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':5,'serialNo':0},'kindCode':'050602','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':5},'currency':'CNY','kindCode':'050602','operateTimeForHis':null,'totalProfit':92.7,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'Y','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':1761.3,'currency1':'CNY','currency2':'','netPremium':1661.6,'modeCode':'','startHour':0,'kindName':'第三者责任险','deductibleRate':0,'rate':0,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':5},'currency':'CNY','kindCode':'050602','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':99.7,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00010 000','basePremium':0,'operateTimeForHis':null,'unitAmount':0,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050052','quantity':0,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':500000,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':1854},{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':7,'serialNo':0},'kindCode':'050711','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':7,'serialNo':0},'kindCode':'050711','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':7,'serialNo':0},'kindCode':'050711','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':7},'currency':'CNY','kindCode':'050711','operateTimeForHis':null,'totalProfit':10.5,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'Y','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':199.5,'currency1':'CNY','currency2':'','netPremium':188.21,'modeCode':'','startHour':0,'kindName':'司机座位责任险','deductibleRate':0,'rate':0.1365,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':7},'currency':'CNY','kindCode':'050711','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':11.29,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00010 000','basePremium':0,'operateTimeForHis':null,'unitAmount':0,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050053','quantity':0,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':100000,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':210},{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':9,'serialNo':0},'kindCode':'050712','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':9,'serialNo':0},'kindCode':'050712','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':9,'serialNo':0},'kindCode':'050712','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':9},'currency':'CNY','kindCode':'050712','operateTimeForHis':null,'totalProfit':10,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'Y','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':190,'currency1':'CNY','currency2':'','netPremium':179.25,'modeCode':'','startHour':0,'kindName':'乘客座位责任险','deductibleRate':0,'rate':0.065,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':9},'currency':'CNY','kindCode':'050712','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':10.75,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00010 000','basePremium':0,'operateTimeForHis':null,'unitAmount':50000,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050053','quantity':4,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':50000,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':200},{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':11,'serialNo':0},'kindCode':'050211','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':11,'serialNo':0},'kindCode':'050211','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':11,'serialNo':0},'kindCode':'050211','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':11},'currency':'CNY','kindCode':'050211','operateTimeForHis':null,'totalProfit':20,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'N','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':380,'currency1':'CNY','currency2':'','netPremium':358.49,'modeCode':'','startHour':0,'kindName':'车身划痕损失险','deductibleRate':0,'rate':0,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':11},'currency':'CNY','kindCode':'050211','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':21.51,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00010 000','basePremium':0,'operateTimeForHis':null,'unitAmount':0,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050059','quantity':0,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':2000,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':400},{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':13,'serialNo':0},'kindCode':'050311','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':13,'serialNo':0},'kindCode':'050311','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':13,'serialNo':0},'kindCode':'050311','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':13},'currency':'CNY','kindCode':'050311','operateTimeForHis':null,'totalProfit':6.17,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'N','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':117.19,'currency1':'CNY','currency2':'','netPremium':110.56,'modeCode':'','startHour':0,'kindName':'自燃损失险','deductibleRate':0,'rate':0.078,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':13},'currency':'CNY','kindCode':'050311','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':6.63,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00010 000','basePremium':0,'operateTimeForHis':null,'unitAmount':0,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050057','quantity':0,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':102800,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':123.36},{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':15,'serialNo':0},'kindCode':'050461','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':15,'serialNo':0},'kindCode':'050461','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':15,'serialNo':0},'kindCode':'050461','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':15},'currency':'CNY','kindCode':'050461','operateTimeForHis':null,'totalProfit':5.24,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'N','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':99.5,'currency1':'CNY','currency2':'','netPremium':93.87,'modeCode':'','startHour':0,'kindName':'涉水行驶损失险','deductibleRate':0,'rate':5,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':15},'currency':'CNY','kindCode':'050461','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':5.63,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00010 000','basePremium':0,'operateTimeForHis':null,'unitAmount':0,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050060','quantity':0,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':0,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':104.74},{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':2,'serialNo':0},'kindCode':'050930','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':2,'serialNo':0},'kindCode':'050930','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':2,'serialNo':0},'kindCode':'050930','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':2},'currency':'CNY','kindCode':'050930','operateTimeForHis':null,'totalProfit':15.71,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'N','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':298.5,'currency1':'CNY','currency2':'','netPremium':281.6,'modeCode':'','startHour':0,'kindName':'车辆损失险-不计免赔','deductibleRate':0,'rate':15,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':2},'currency':'CNY','kindCode':'050930','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':16.9,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00000 000','basePremium':0,'operateTimeForHis':null,'unitAmount':0,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050066','quantity':0,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':0,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':314.21},{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':4,'serialNo':0},'kindCode':'050932','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':4,'serialNo':0},'kindCode':'050932','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':4,'serialNo':0},'kindCode':'050932','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':4},'currency':'CNY','kindCode':'050932','operateTimeForHis':null,'totalProfit':6.55,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'N','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':124.36,'currency1':'CNY','currency2':'','netPremium':117.32,'modeCode':'','startHour':0,'kindName':'全车盗抢险-不计免赔','deductibleRate':0,'rate':20,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':4},'currency':'CNY','kindCode':'050932','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':7.04,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00000 000','basePremium':0,'operateTimeForHis':null,'unitAmount':0,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050066','quantity':0,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':0,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':130.91},{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':6,'serialNo':0},'kindCode':'050931','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':6,'serialNo':0},'kindCode':'050931','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':6,'serialNo':0},'kindCode':'050931','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':6},'currency':'CNY','kindCode':'050931','operateTimeForHis':null,'totalProfit':13.91,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'N','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':264.2,'currency1':'CNY','currency2':'','netPremium':249.25,'modeCode':'','startHour':0,'kindName':'第三者责任险-不计免赔','deductibleRate':0,'rate':15,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':6},'currency':'CNY','kindCode':'050931','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':14.95,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00000 000','basePremium':0,'operateTimeForHis':null,'unitAmount':0,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050066','quantity':0,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':0,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':278.1},{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':8,'serialNo':0},'kindCode':'050933','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':8,'serialNo':0},'kindCode':'050933','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':8,'serialNo':0},'kindCode':'050933','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':8},'currency':'CNY','kindCode':'050933','operateTimeForHis':null,'totalProfit':1.58,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'N','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':29.93,'currency1':'CNY','currency2':'','netPremium':28.24,'modeCode':'','startHour':0,'kindName':'司机座位责任险-不计免赔','deductibleRate':0,'rate':15,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':8},'currency':'CNY','kindCode':'050933','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':1.69,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00000 000','basePremium':0,'operateTimeForHis':null,'unitAmount':0,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050066','quantity':0,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':0,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':31.5},{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':10,'serialNo':0},'kindCode':'050934','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':10,'serialNo':0},'kindCode':'050934','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':10,'serialNo':0},'kindCode':'050934','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':10},'currency':'CNY','kindCode':'050934','operateTimeForHis':null,'totalProfit':1.5,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'N','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':28.5,'currency1':'CNY','currency2':'','netPremium':26.89,'modeCode':'','startHour':0,'kindName':'乘客座位责任险-不计免赔','deductibleRate':0,'rate':15,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':10},'currency':'CNY','kindCode':'050934','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':1.61,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00000 000','basePremium':0,'operateTimeForHis':null,'unitAmount':0,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050066','quantity':0,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':0,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':30},{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':12,'serialNo':0},'kindCode':'050937','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':12,'serialNo':0},'kindCode':'050937','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':12,'serialNo':0},'kindCode':'050937','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':12},'currency':'CNY','kindCode':'050937','operateTimeForHis':null,'totalProfit':3,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'N','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':57,'currency1':'CNY','currency2':'','netPremium':53.77,'modeCode':'','startHour':0,'kindName':'车身划痕损失险不计免赔','deductibleRate':0,'rate':15,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':12},'currency':'CNY','kindCode':'050937','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':3.23,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00000 000','basePremium':0,'operateTimeForHis':null,'unitAmount':0,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050066','quantity':0,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':0,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':60},{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':14,'serialNo':0},'kindCode':'050935','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':14,'serialNo':0},'kindCode':'050935','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':14,'serialNo':0},'kindCode':'050935','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':14},'currency':'CNY','kindCode':'050935','operateTimeForHis':null,'totalProfit':1.23,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'N','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':23.44,'currency1':'CNY','currency2':'','netPremium':22.11,'modeCode':'','startHour':0,'kindName':'自燃损失险不计免赔','deductibleRate':0,'rate':20,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':14},'currency':'CNY','kindCode':'050935','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':1.33,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00000 000','basePremium':0,'operateTimeForHis':null,'unitAmount':0,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050066','quantity':0,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':0,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':24.67},{'ratePeriod':0,'prpCprofits':[{'handlerCode':'11095373','prpCprofitDetails':[{'profitName':'无赔款优待及上年赔款记录','kindName':'','chooseFlag':'1','profitRateMin':100,'fieldValue':0,'flag':'','id':{'profitCode':'C01','proposalNo':'','profitType':'1','itemKindNo':16,'serialNo':0},'kindCode':'050938','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'新保或上年发生1次赔款','insertTimeForHis':null,'conditionCode':'C0104','profitRateMax':130,'profitRate':100},{'profitName':'自主渠道系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C02','proposalNo':'','profitType':'1','itemKindNo':16,'serialNo':0},'kindCode':'050938','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'经纪及代理渠道业务优惠系数','insertTimeForHis':null,'conditionCode':'C0206','profitRateMax':125,'profitRate':90},{'profitName':'自主核保优惠系数','kindName':'','chooseFlag':'1','profitRateMin':75,'fieldValue':0,'flag':'','id':{'profitCode':'C03','proposalNo':'','profitType':'1','itemKindNo':16,'serialNo':0},'kindCode':'050938','prpCprofit':null,'profitPeriod':0,'operateTimeForHis':null,'riskCode':'','condition':'自主核保优惠系数','insertTimeForHis':null,'conditionCode':'C03  ','profitRateMax':125,'profitRate':105.555556}],'minusFlag':'','prpCitemKind':null,'disCount':0.95,'flag':'','id':{'proposalNo':'','profitType':'1','itemKindNo':16},'currency':'CNY','kindCode':'050938','operateTimeForHis':null,'totalProfit':0.79,'approverCode':'','riskCode':'DAA','operatorCode':'99355911','insertTimeForHis':null,'inputDate':{'date':22,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1479744000000}}],'calculateFlag':'N','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':14.92,'currency1':'CNY','currency2':'','netPremium':14.08,'modeCode':'','startHour':0,'kindName':'涉水行驶损失险不计免赔','deductibleRate':0,'rate':15,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':16},'currency':'CNY','kindCode':'050938','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':0.84,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':0,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 00000 000','basePremium':0,'operateTimeForHis':null,'unitAmount':0,'riskCode':'','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050066','quantity':0,'value':0,'disCount':0.95,'itemDetailName':'车辆','amount':0,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':15.71}],'prpCitemCarExt':{'lastDamagedBI':0,'noDamYearsCI':0,'lastDamagedCI':0,'noDamYearsBI':0,'thisDamagedB':0,'thisOffenceCI':0,'thisDamagedA':0,'damFloatRatioCI':0,'flag':'','thisDamagedCI':0,'lastOffenceCI':0,'thisDamagedBI':0,'id':{'proposalNo':'','itemNo':1},'offFloatRatioCI':0,'noOffYearsCI':0,'operateTimeForHis':null,'rateRloatFlag':'','riskCode':'DAA','lastDamagedA':0,'lastDamagedB':0,'insertTimeForHis':null,'prpCitemCar':null},'ciInsureDemandCheckVoList':[],'claimsMessage':'','ciInsureDemandRepets':[],'ciEndorDemandDAA':{'formula':'','driverRateReason':'','lastpolicyexpiredate':null,'formerTax':0,'transferDate':null,'preferentialDay':'','commissionRate':0,'resureFundFee':0,'lastpolicyquerydate':null,'cancelTax':0,'driverCoeff':0,'responseRemark':'','applyNo':'','dummyrequestremark':'','areaCode':'','operatorCode':'','netPremium':0,'districtCoeff':0,'policyNo':'','endorseReasonDesc':'','lastExpireDate':null,'peccancyCoeff':0,'comCode':'','reinsureFlag':'','currentTax':0,'amendBasedPremium':0,'ciCoveragePremiums':[],'insertTimeForHis':null,'lastproducercode':'','taxPremium':0,'lastBillDate':null,'isAmendTax':'','coveragecode':'','lateFee':0,'lastpolicybilldate':null,'ciEndorDemandLosses':[],'flag':'','dummyresponseremark':'','coveragetype':'','operateTimeForHis':null,'claimCoeff':0,'lastEffectiveDate':null,'taxRate':0,'endorseDemandNo':'','demandTime':null,'claimAdjustReason':'','ptext':'','policyValidNo':'','lastpolicytotalpremium':null,'endorseReasonCode':'','lastpolicyeffectivedate':null,'taxAmendPremium':0,'chgPremium':0,'amendStandardPremium':0,'taxFlag':'','endorseNo':'','endorseValidNo':'','vehicleOwnerMessageType':'','remark':'','amendquerypastdate':null,'restricFlag':'','ciEndorDemandPaies':[],'rateFloatFlag':'','requestRemark':'','drawReason':'','taxAmendDeclare':'','peccancyAdjustReason':'','disTrictRateReason':''},'ciEndorRiskItem':null,'errorMessageVo':{'errorCode':'0000','flag':'0','errorMessage':'1'},'ciLastPolicyInfo':null}],'prpCsalesFixes':[],'errMessage':null,'cIInsureMotorFlag':'1','ciInsureVOList':[{'prpCfixations':[{'realProfits':0,'operationInfo':'折扣以系统报价为准','riskClass':'B','riskPremium':37945,'taxorAppend':5.6,'flag':'','discount':100,'profits':5,'cost':4.31,'payMentR':60.68,'basePremium':76914.88,'basePayMentR':65.13,'costRate':0,'operateTimeForHis':null,'signPremium':73587,'prpCmain':null,'responseCode':'','poundAge':27.5,'id':{'proposalNo':'','riskCode':'DZA'},'riskSumPremium':0,'profitClass':'','isQuotation':'','remark':'','realDisCount':0,'realPayMentR':0,'insertTimeForHis':null,'errorMessage':''}],'ciInsureDemandRisk':null,'errMessage':'','ciRiskWarningClaimItems':[],'ciInsureDemandCheckVo':{'checkQuestion':'','validCheckDate':'','demandNo':'','riskCode':'','checkCode':'','errMessage':'','renewalFlag':'','flag':'','checkAnswer':''},'ciInsureDemand':{'policyadjustvalue':0,'tonCount':0,'frameNo':'','lastpolicyexpiredate':null,'demandNo':'01PICC440316001480313726346433','demandeffectendtime':null,'insurerArea':'','preferentialDay':0,'commissionRate':0,'resureFundFee':0,'previousPay':0,'lastpolicyquerydate':null,'extendChar1':'','proposalNo':'','responseRemark':null,'premium':950,'operatorCode':'99355911','enrollDate':null,'districtCoeff':0,'chgVehicleMessage':'','ciCommissionMessage':{'ciCertificates':[{'certificateValidDate':{'date':19,'day':1,'timezoneOffset':-480,'year':103,'month':4,'hours':0,'seconds':0,'minutes':0,'time':1053273600000},'certificateId':{'certificateId':1,'demandNo':'01PICC440316001480313726346433'},'ciCommissionMessage':null,'certificateExpireDate':{'date':18,'day':3,'timezoneOffset':-480,'year':116,'month':4,'hours':0,'seconds':0,'minutes':0,'time':1463500800000}},{'certificateValidDate':{'date':28,'day':4,'timezoneOffset':-480,'year':116,'month':3,'hours':0,'seconds':0,'minutes':0,'time':1461772800000},'certificateId':{'certificateId':2,'demandNo':'01PICC440316001480313726346433'},'ciCommissionMessage':null,'certificateExpireDate':{'date':17,'day':5,'timezoneOffset':-480,'year':119,'month':4,'hours':0,'seconds':0,'minutes':0,'time':1558022400000}}],'newVehicleEffectReason':'','commissionRateUpper':0.04,'individualProducerCode':'440321100061','practiceCertificateCode':'','demandNo':'01PICC440316001480313726346433','groupCompany':'','producerEffectReason':'','ciInsureDemandContracts':[{'operateTimeForHis':null,'ciCommissionMessage':null,'contractExpireDate':{'date':18,'day':3,'timezoneOffset':-480,'year':116,'month':4,'hours':0,'seconds':0,'minutes':0,'time':1463500800000},'contractValidDate':{'date':21,'day':2,'timezoneOffset':-480,'year':113,'month':4,'hours':0,'seconds':0,'minutes':0,'time':1369065600000},'proposalno':'','insertTimeForHis':null,'contractno':'440321112020600','id':{'serialno':1,'demandNo':'01PICC440316001480313726346433'}},{'operateTimeForHis':null,'ciCommissionMessage':null,'contractExpireDate':{'date':30,'day':3,'timezoneOffset':-480,'year':115,'month':8,'hours':0,'seconds':0,'minutes':0,'time':1443542400000},'contractValidDate':{'date':2,'day':1,'timezoneOffset':-480,'year':114,'month':5,'hours':0,'seconds':0,'minutes':0,'time':1401638400000},'proposalno':'','insertTimeForHis':null,'contractno':'440321114061200','id':{'serialno':2,'demandNo':'01PICC440316001480313726346433'}},{'operateTimeForHis':null,'ciCommissionMessage':null,'contractExpireDate':{'date':17,'day':5,'timezoneOffset':-480,'year':119,'month':4,'hours':0,'seconds':0,'minutes':0,'time':1558022400000},'contractValidDate':{'date':19,'day':0,'timezoneOffset':-480,'year':113,'month':4,'hours':0,'seconds':0,'minutes':0,'time':1368892800000},'proposalno':'','insertTimeForHis':null,'contractno':'440321116051900','id':{'serialno':3,'demandNo':'01PICC440316001480313726346433'}}],'accountNumber':4000021219200320544,'accountName':'深圳市宜保通保险销售有限公司','ciInsureDemand':null,'bankCode':'工商银行','bankName':'中国工商银行深圳市红围支行','agentTypeCode':'3','operateTimeForHis':null,'certificateNo':'203131000000800','individualProducerName':'深圳市宜保通保险销售有限公司','claimEffectReason':'','insertTimeForHis':null,'companyCommissionRateUpper':0},'ciRiskWarningClaimItems':[],'reinsureFlag':'0','usbkey':'','carOwner':'','ciCoveragePremiums':[],'rescueFundRate':0,'lastproducercode':'','disPlacement':'','posNo':'','lastpolicybilldate':null,'carStatus':'','flag':'','ineffectualDate':null,'dummyresponseremark':'','rateRloatFlag':'01','lastEffectiveDate':null,'certificateDate':null,'licenseColorCode':'','demandTime':{'date':28,'day':1,'timezoneOffset':-480,'year':116,'month':10,'hours':14,'seconds':28,'minutes':15,'time':1480313728071},'lastpolicytotalpremium':null,'exhaustCapacity':0,'carKindCode':'','amount':122000,'taxFlag':'','brandName':'','computerip':'','preferentialPremium':0,'adjustEnd':null,'remark':'成功','restricFlag':'','preferentialFormula':'','requestRemark':null,'benchMarkPremium':950,'seatCount':0,'peccancyAdjustReason':'','pmVehicleMessage':'','coverageCode':'','prevalidno':'','busilastyearstartdate':null,'driverRateReason':'','busiInsurerArea':'','manufacturerName':'','lastyearenddate':null,'validCheckDate':null,'ipPart':'','coverageType':'','transferDate':null,'engineNo':'','adjustStart':null,'driverCoeff':0,'dummyrequestremark':'','areaCode':'','netPremium':0,'endValidDate':null,'useNatureCode':'','policyNo':'','lastExpireDate':null,'peccancyCoeff':0,'busiLastYearEndDate':null,'licenseNo':'','comCode':'44030716','taxActual':0,'proconfirmenddate':'','vehicleCategory':'','modelCode':'','insertTimeForHis':null,'ciLastPolicyInfo':null,'taxPremium':0,'startDate':null,'lastBillDate':{'date':28,'day':1,'timezoneOffset':-480,'year':116,'month':10,'hours':14,'seconds':28,'minutes':15,'time':1480313728071},'lateFee':0,'salePrice':'','haulage':'','taxTotal':'','fuelType':'','makeDate':null,'pmUserType':'','basePremium':950,'operateTimeForHis':null,'useTypeSource':'','claimCoeff':0,'wholeWeight':'','taxRate':0,'useTypeMessage':'','ownerName':'','licenseType':'','claimAdjustReason':'','checkDate':null,'lastpolicyeffectivedate':null,'channeltype':'','lastyearstartdate':null,'querypastdate':null,'endDate':null,'vehicleOwnerMessageType':'','brandCName':'','dzflag':'','noVehicleMessageType':'','districtRateReason':'','proconfirmstartdate':''},'prpCitemKinds':[{'ratePeriod':0,'prpCprofits':[],'calculateFlag':'Y','addressNo':0,'exchange2':0,'exchange1':0,'unit':'','pureRiskPremium':0,'amount1':0,'exchange3':0,'amount2':0,'endHour':24,'amount3':0,'buyDate':null,'currency3':'','premium':0,'currency1':'CNY','currency2':'','netPremium':896.23,'modeCode':'','startHour':0,'kindName':'交强险','deductibleRate':0,'rate':0,'dutyFlag':'2','id':{'proposalNo':'','itemKindNo':1},'currency':'CNY','kindCode':'050100','shortRateFlag':'2','modeName':'','familyName':'','insertTimeForHis':null,'projectCode':'1','model':'','taxPremium':53.77,'startDate':{'date':23,'day':5,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1482422400000},'adjustRate':1,'familyNo':1,'premium1':0,'premium2':0,'premium3':0,'riskPremium':0,'flag':' 100000000','basePremium':0,'operateTimeForHis':null,'unitAmount':0,'riskCode':'DZA','taxRate':6,'prpCmain':null,'itemNo':1,'itemCode':'0001','clauseCode':'050001','quantity':0,'value':0,'disCount':1,'itemDetailName':'车辆','amount':122000,'endDate':{'date':22,'day':5,'timezoneOffset':-480,'year':117,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1513872000000},'shortRate':100,'deductible':0,'benchMarkPremium':0}],'ciInsureTax':{'declareStatus':'0','taxPayerName':'','taxConditionCode':'T','calctaxFlag':'1','taxPayerIdentificationCode':'','sumTax':70,'policyNo':'','sumOverdue':0,'declareDate':'','demandNo':'01PICC440316001480313726346433','flag':'','annualTaxDue':70,'quotationNo':'','proposalNo':'','taxTermTypeCode':'08','operateTimeForHis':null,'taxDescription':'','sumTaxDefault':0,'ciInsureAnnualTaxes':[{'exceedDate':null,'overDue':0,'taxDepartment':'','taxStartDate':{'date':1,'day':2,'timezoneOffset':-480,'year':116,'month':10,'hours':0,'seconds':0,'minutes':0,'time':1477929600000},'flag':'1','ciInsureTax':null,'proposalNo':'','ciInsureTaxRates':[],'unitRate':'420.0','operateTimeForHis':null,'taxDue':70,'taxUnitTypeCode':'1','policyNo':'','totalAmount':70,'declareDate':null,'ciInsureDerateTaxes':[{'deductionDueProportion':0,'policyNo':'','deduction':0,'ciInsureAnnualTax':null,'taxDepartment':'','deductionDocumentNumber':'','flag':'','id':{'demandNo':'01PICC440316001480313726346433','serialNo':1},'quotationNo':'','proposalNo':'','operateTimeForHis':null,'taxDepartmentCode':'','deductionDueCode':'','deductionDueType':'','insertTimeForHis':null}],'taxLocationCode':'440300','taxDocumentNumber':'','id':{'demandNo':'01PICC440316001480313726346433','serialNo':1},'exceedDaysCount':0,'quotationNo':'','annualTaxAmount':420,'taxEndDate':{'date':31,'day':6,'timezoneOffset':-480,'year':116,'month':11,'hours':0,'seconds':0,'minutes':0,'time':1483113600000},'taxDepartmentCode':'','insertTimeForHis':null}],'insertTimeForHis':null,'taxRegistryNumber':'','taxAmountFlag':'1'},'ciInsureDemandLossList':[],'ciInsureDemandPayList':[],'ciEndorValid':null,'taxAbate':null,'errorMessageVo':{'errorCode':'0000','flag':'0','errorMessage':'折扣以系统报价为准'},'ciCarShipTax':{'taxpayerNo':'','thisPayTax':0,'policyNo':'','payStartDate':null,'sumPayTax':0,'carNumber':'','delayPayTax':0,'demandNo':'','taxPayerCertiCode':'','flag':'','poCategory':'','payId':'','poWeight':0,'taxFlag':'','freeNo':'','proposalNo':'','payEndDate':null,'remarks':'','taxPayerCertiType':'','taxComName':'','dutyPaidProofNo':'','prePayTax':0}}]}]}";
		 Map<String,String> checkbfSaveParams = new HashMap<String,String>();
		 Map<String,String> params =getQuoteParam(req,checkbfSaveParams);
		 RenbaoQuoteContent quoteContent = JSON.parseObject(body,RenbaoQuoteContent.class);
		 CarQuoteRes res = getResQuote(req,quoteContent);//封装报价
		 Map<String,String> saveParmas = new QuoteSave().save(params,checkbfSaveParams, quoteContent,res,quoteReferer,cookie,"");
		 return saveParmas;
	}
*/
	/*public Map<String,String> getQuote(CarQuoteReq req) throws Exception{
		 String cookie = RenBaoLoginA.login();
		 String prepareEditReferer = "http://10.134.130.208:8000/prpall/menu/showMenu.do?systemCode=prpall&userCode=99355911";
		 String quoteUrl = "http://10.134.130.208:8000/prpall/business/caculatePremiunForFG.do";
		 String quoteReferer = "http://10.134.130.208:8000/prpall/business/prepareEdit.do?bizType=PROPOSAL&editType=NEW";
		 String prepareEditBody = RenBaoLoginA.get(quoteReferer, "GBK", cookie, prepareEditReferer, null);
		 Document doc = Jsoup.parse(prepareEditBody);
		 String randomProposalNo = doc.getElementById("randomProposalNo").val();
		 Map<String,String> checkbfSaveParams = new HashMap<String,String>();
		 Map<String,String> sumCiParams = new HashMap<String,String>();
		 Map<String,String> params =getQuoteParam(req,checkbfSaveParams,randomProposalNo);
		 System.out.println("quote_params-->"+params);
		 String body = RenBaoLoginA.post(quoteUrl,params,"GBK",cookie,quoteReferer);//保费计算
		 RenbaoQuoteContent quoteContent = JSON.parseObject(body,RenbaoQuoteContent.class);
		 System.out.println("body-->"+body);
		 System.out.println("quoteContent-->"+JSON.toJSONString(quoteContent));
		 CarQuoteRes res = getResQuote(req,quoteContent,sumCiParams);//封装报价
		 System.out.println("renbao quote res-->"+JSON.toJSONString(res));
		 //params.putAll(saveParams);//添加商业险总保费、总净保费、总税额参数
		 return new QuoteSave().save(params,sumCiParams,checkbfSaveParams,quoteContent,res,quoteReferer,cookie,"");//保存
	}*/
	
	public CarQuoteRes getQuote(CarQuoteReq req) throws Exception{
		 CarQuoteRes res = new CarQuoteRes();
		 String cookie = RenBaoLoginA.login();
		 String prepareEditReferer = "http://10.134.130.208:8000/prpall/menu/showMenu.do?systemCode=prpall&userCode=99355911";
		 String quoteUrl = "http://10.134.130.208:8000/prpall/business/caculatePremiunForFG.do";
		 String quoteReferer = "http://10.134.130.208:8000/prpall/business/prepareEdit.do?bizType=PROPOSAL&editType=NEW";
		 String randomProposalNo="";//报价单随机号
		 try {
			String prepareEditBody = RenbaoClientSSLUtil.get(quoteReferer, "GBK", cookie, prepareEditReferer, null);//加载投保页面
			randomProposalNo = Jsoup.parse(prepareEditBody).getElementById("randomProposalNo").val();
		 } catch (Exception e) {
			 res.getHeader().setResCode(Constants.FAIL);
			 res.getHeader().setResMsg("投保单录入页面加载错误!");
			 return res;
		 }
		 Map<String,String> checkbfSaveParams = new HashMap<String,String>();//保费计算结果保存检验参数
		 Map<String,String> sumCiParams = new HashMap<String,String>();//商业险总保费、总净保费、总税额参数
		 RenbaoQuoteService quoteService = new RenbaoQuoteService();
		 Map<String,String> params =quoteService.getQuoteParam(req,res,checkbfSaveParams,randomProposalNo,cookie);//保费计算请求参数
		 if(StringUtil.ObjectToString(res.getHeader().getResCode()).equals(Constants.FAIL))
			 return res;
		 String body="";
		 try {
			body = RenbaoClientSSLUtil.post(quoteUrl,params,"GBK",cookie,quoteReferer);//保费计算请求
		 } catch (Exception e) {
			 res.getHeader().setResCode("11000");
			 res.getHeader().setResMsg("保费计算错误"+quoteUrl);
			 return res;
		 }
		 RenbaoQuoteContent quoteContent = JSON.parseObject(body,RenbaoQuoteContent.class);
		 quoteService.getResQuote(req,res,quoteContent,sumCiParams);//封装报价结果
		 RenbaoQuoteSaveService saveService = new RenbaoQuoteSaveService();
		 //saveService.save(params,sumCiParams,checkbfSaveParams,quoteContent,res,quoteReferer,cookie,"");//保存
		 return res;
	}

	public Map<String,String> getQuoteParam(CarQuoteReq req,Map<String,String> checkbfSaveParams,String randomProposalNo) throws Exception{
		
		Map<String,Object> paramObject = RenbaoConfig.getQuoteParamsMap();//参数模板
		Map<String,String> params = new HashMap<String,String>();
		for(String key :paramObject.keySet()){
			params.put(key,(String) paramObject.get(key));
		}
		params.put("randomProposalNo", randomProposalNo);//华安系统生成随机码
		checkbfSaveParams.putAll(SetRelatePersion(req));//关系人-投保人、被保人、车主
		checkbfSaveParams.putAll(setCarLicenseInfo(req));//行驶证及车辆信息
		checkbfSaveParams.putAll(getDate(req));//交强、商业险日期
		checkbfSaveParams.put("prpCitemCar.frameNo",req.getCarLicenseInfo().getVin());//车架号
		checkbfSaveParams.put("prpCitemCar.licenseFlag",String.valueOf(req.getLicenseFlag()));//是否已上牌照--牌照标志，1-已上牌，0-新车未上牌，默认为1
		checkbfSaveParams.put("prpCitemCar.runAreaCode",req.getRunAreaCode());//行驶区域11
		
		 params.putAll(setCarModelInfo(req));//车型信息
		 params.putAll(setKindItemBiCI(req));//商业险、交强险
		 params.putAll(checkbfSaveParams);
		
		return params;
	
	}
	
	public Map<String,String> getDate(CarQuoteReq req){
		 Map<String,String> relateMap = new HashMap<String,String>();
		   relateMap.put("prpCmain.startDate",req.getStartDateBI());
		   relateMap.put("prpCmain.endDate",req.getEndDateBI());
		   relateMap.put("prpCmainCI.startDate",req.getStartDateCI());
		   relateMap.put("prpCmainCI.endDate",req.getEndDateCI());
		   relateMap.put("prpCmain.startHour", "0");
		   relateMap.put("prpCmain.endHour", "24");
		   relateMap.put("prpCmainCI.startHour", "0");
		   relateMap.put("prpCmainCI.endHour", "24");
		   relateMap.put("prpCmain.riskCode", "DAA");
		   relateMap.put("prpCmainCI.proposalNo", "");
		   relateMap.put("editType", "NEW");
		   relateMap.put("prpCmain.proposalNo", "");
		   relateMap.put("prpCmain.sumPremium", "");
		   relateMap.put("prpCmainCI.sumPremium", "");
		   relateMap.put("prpCmainCommon.DBCFlag", "0");
		return relateMap;
	}
	
	//组装商业、交强险参数
	 public Map<String,String> setKindItemBiCI(CarQuoteReq req) throws Exception{
		 Map<String,String> params = new HashMap<String,String>();
		 int index =0;
		 for(CarQuoteInsItemVo item:req.getCarQuoteInsItemList()){
			 if(item.getCategory()==0){//交强险
				 params.put("prpCitemKindCI.amount",item.getInsuredAmount());//保额/限额
				 params.put("prpCitemKindCI.kindCode",(String) RenbaoConfig.getRkindCodeMap().get(item.getKindCode()));//险种代码转换
				 params.put("prpCitemKindCI.kindName",CarInsuranceConstant.getCarInsuranceTypeMap().get(item.getKindCode()));//险种代码转换
				 continue;
			 }
			 params.putAll(getParamsTemplate(RenbaoConfig.getBiParamsMap(),index));//商业险参数模版
			 params.putAll(getParamsTemplate((Map<String, Object>) RenbaoConfig.getSBiParamsMap().get(item.getKindCode()),index));//商业险特殊参数
			 params.put("prpCitemKindsTemp["+index+"].specialFlag",item.getDeductibleFlag()==1?"on":"");//不计免赔标志
			 params.put("prpCitemKindsTemp["+index+"].kindName",CarInsuranceConstant.getCarInsuranceTypeMap().get(item.getKindCode()));//购买险种标志
			 params.put("prpCitemKindsTemp["+index+"].kindCode",(String) RenbaoConfig.getRkindCodeMap().get(item.getKindCode()));////险种代码替换
			 params.put("prpCitemKindsTemp["+index+"].amount",item.getInsuredAmount());//保额/限额
			 String deduCode = (String) RenbaoConfig.getRkindCodeMap().get("D"+item.getKindCode());//不计免赔险种代码
			 params.put("relateSpecial["+index+"]",deduCode);
			 if(item.getKindCode().equals("4")){//乘客险
				 params.put("prpCitemKindsTemp["+index+"].unitAmount",item.getInsuredAmount());
				 params.put("prpCitemKindsTemp["+index+"].quantity",item.getSeatNum());	
			 }else if(item.getKindCode().equals("6")){//玻璃破粹险
				 params.put("prpCitemKindsTemp["+index+"].modeCode","10");//玻璃类型 10国产玻璃
			 }
			 index = index +1;
			 if(item.getDeductibleFlag()==1){//不计免赔
				 params.put("prpCitemKindsTemp["+index+"].kindCode",deduCode);
				 params.put("prpCitemKindsTemp["+index+"].kindName",CarInsuranceConstant.getCarInsuranceTypeMap().get("D"+item.getKindCode()));
				 params.putAll(getParamsTemplate(RenbaoConfig.getDeduBiParamsMap(),index));//不计免赔险参数模版
				 index = index+1;
			 }
		 }
		 System.out.println("kind code params"+params);
		 return params;
	 }
	 public Map<String,String> getParamsTemplate(Map<String,Object> map,int index){
		 Map<String,String> params = new HashMap<String,String>();
		 for(String key:map.keySet()){
			 params.put(key.replace("index",String.valueOf(index)),(String) map.get(key));
		 }
		 return params;
	 }

	/*//商业险
	 public  Map<String,String> getkindItemBIMap(String index) throws Exception{
		  Map<String,String> relateMap = new HashMap<String,String>();
		  //relateMap.put("kindCode", "prpCitemKindsTemp["+index+"].kindCode");//险种代码
		  //relateMap.put("kindName", "prpCitemKindsTemp["+index+"].kindName");//险种名称
		  //relateMap.put("seatNum", "prpCitemKindsTemp["+index+"].quantity");// 司机（乘客）责任险的座位数
		  //relateMap.put("insuredAmount", "prpCitemKindsTemp["+index+"].amount");//保额/限额
		  //relateMap.put("deductibleFlag", "prpCitemKindsTemp["+index+"].specialFlag");//购买不计免赔特约险标志
		  return relateMap;
	 }
	 //交强险
	 public  Map<String,String> getkindItemCIMap() throws Exception{
		  Map<String,String> relateMap = new HashMap<String,String>();
		  relateMap.put("kindCode", "prpCitemKindCI.kindCode");//险种代码
		  relateMap.put("kindName", "prpCitemKindCI.kindName");//险种名称
		  relateMap.put("insuredAmount", "prpCitemKindCI.amount");//保额/限额
		  return relateMap; 
	  }*/
	//关系人-投保人/被保人/车主
	public Map<String,String> SetRelatePersion(CarQuoteReq req) throws Exception{
		Map<String,String> params = new HashMap<String,String>();
		Map<String, String> insureRelateMap = getRelatePersionMap("1");
		Map<String, String> assureRelateMap = getRelatePersionMap("2");
		Map<String, String> ownerRelateMap = getCarOwnerMap("3");
		Map<String,String> insureParams = RenbaoUtil.getValueByClazz1(req.getCarInsurerInfo(), insureRelateMap);
		Map<String,String> asssureParams = RenbaoUtil.getValueByClazz1(req.getCarAssuredInfo(), assureRelateMap);
		Map<String,String> ownerParams = RenbaoUtil.getValueByClazz1(req.getCarOwnerInfo(),ownerRelateMap);
		insureParams.put("prpCinsureds[1].insuredFlag" , "10000000000000000000000000000A");//投保人标志
		asssureParams.put("prpCinsureds[2].insuredFlag", "010000000000000000000000000000");//被保人标志
		ownerParams.put("prpCinsureds[3].insuredFlag"  , "001000000000000000000000000000");//车主标志
		params.putAll(insureParams);
		params.putAll(asssureParams);
		params.putAll(ownerParams);
		return params;
	}
	//关系人-投保人/被保人
	public Map<String,String> getRelatePersionMap(String index) throws Exception {
		Map<String, String> relateMap = new HashMap<String, String>();
		//relateMap.put("relationType", "_" + String.valueOf(relationType));// 关系人类型// 1-投保人;2-被保人;3-收件人
		relateMap.put("name", "prpCinsureds[" + index + "].insuredName");// 姓名
		relateMap.put("sex", "prpCinsureds[" + index + "].sex");// 性别 1-男,2-女
		relateMap.put("age", "prpCinsureds[" + index + "].age");// 年龄
		relateMap.put("idType", "prpCinsureds[" + index + "].identifyType");// 证件类型
		relateMap.put("idNo", "prpCinsureds[" + index + "].identifyNumber");// 证件号码
		relateMap.put("telePhone", "prpCinsureds[" + index + "].phoneNumber");// 固定电话
		relateMap.put("mobilePhone", "prpCinsureds[" + index + "].mobile");// 手机号码
		relateMap.put("address", "prpCinsureds[" + index + "].insuredAddress");// 居住地址
		relateMap.put("postCode", "prpCinsureds[" + index + "].postCode");// 邮政编码
		return relateMap;
	}
	//车主
	public Map<String,String> getCarOwnerMap(String index) throws Exception {
		Map<String, String> relateMap = new HashMap<String, String>();
		relateMap.put("carOwner", "prpCinsureds[" + index + "].insuredName");// 车主姓名
		relateMap.put("ownerPhone", "prpCinsureds[" + index + "].mobile");// 车主手机号码
		relateMap.put("ownerIdType", "prpCinsureds[" + index + "].identifyType");// 车主证件类型
		relateMap.put("ownerIdentifyNumber", "prpCinsureds[" + index + "].identifyNumber");// 车主证件号码
		relateMap.put("ownerAddr", "prpCinsureds[" + index + "].insuredAddress");// 车主详细地址信息
		relateMap.put("ownerSex", "prpCinsureds[" + index + "].sex");// 车主姓别,1为男，2为女
		relateMap.put("ownerAge", "prpCinsureds[" + index + "].age");// 车主年龄
		//relateMap.put("ownerBirthday", "");// 车主出生日期，日期格式为yyyy-mm-dd
		return relateMap;
	}
	//车型信息
	public Map<String,String> setCarModelInfo(CarQuoteReq req) throws Exception{
		Map<String,String> relateMap = new HashMap<String,String>();
		relateMap.put("modelCode","prpCitemCar.modelCode");//车型编码--车型代码
		relateMap.put("purchasePrice","prpCitemCar.actualValue");//实际价值 111800.00--新车购置价
		relateMap.put("brandName","prpCitemCar.brandName");//车型名称--车型名称(品牌型号)
		relateMap.put("seatCount","prpCitemCar.seatCount");//核定载客量(人)--吨位数
		relateMap.put("tonCount","prpCitemCar.tonCount");//核定载质量(千克)--座位数
		relateMap.put("exhaustScale","prpCitemCar.exhaustScale");//排量/功率
		relateMap.put("fullWeight","prpCitemCar.carLotEquQuality");//整备质量(千克)
		relateMap.put("countryNature","prpCitemCar.countryNature");//进口/国产类--国别性质：01-国产、02-进口、03-合资
		relateMap.put("carVehicleType","prpCitemCar.carKindCode");//车辆种类 -- 车辆种类:1为客车，2为货车，3为特种车
		relateMap.put("remark","prpCitemCar.remark");
		Map<String,String> params = RenbaoUtil.getValueByClazz1(req.getCarModelInfo(), relateMap);
		System.out.println(params);
		return params;
	}
	//行驶证及相关行驶信息
	public Map<String,String> setCarLicenseInfo(CarQuoteReq req) throws Exception{
		Map<String,String> relateMap = new HashMap<String,String>();
		relateMap.put("licenseNo","prpCitemCar.licenseNo");//号牌号码
		relateMap.put("engineNo","prpCitemCar.engineNo");//发动机号
		relateMap.put("vin","prpCitemCar.vinNo");//VIN码
		//relateMap.put("","prpCitemCar.frameNo","vin");//车架号
		relateMap.put("enrollDate","prpCitemCar.enrollDate");//初登日期
		relateMap.put("chgOwnerFlag","prpCitemCar.transferVehicleFlag");//是否为过户车
		relateMap.put("chgOwnerDate","prpCitemCar.transferDate");//过户日期
		relateMap.put("useNature","prpCitemCar.useNatureCode");//使用性质--车辆使用性质,1运营，2非运营，默认为2
		relateMap.put("licenseType","prpCitemCar.licenseType");//号牌种类 --号牌种类：01为大型车，02为小型车，16为教练汽车，22为临时行驶车，默认为02
		relateMap.put("nonLocalFlag","prpCitemCar.noNlocalFlag");//是否外地车--外地车标志，1-外地车，0-本地车，默认为0
		Map<String,String> params = RenbaoUtil.getValueByClazz1(req.getCarLicenseInfo(), relateMap);
		return params;
	}
	//封装报价结果
    public CarQuoteRes getResQuote(CarQuoteReq carQuoteReq,RenbaoQuoteContent quoteContent,Map<String,String> saveParams){
		CarQuoteRes qRes = getQuote(quoteContent,saveParams);
		qRes.setMerchantOrderId(carQuoteReq.getMerchantOrderId());
		if (qRes.getCarQuoteInfo() == null) {
			CarQuoteInfoVo infoVo = new CarQuoteInfoVo();
			qRes.setCarQuoteInfo(infoVo);
			infoVo.setInsurerId(carQuoteReq.getInsurerId() == null ? "" : carQuoteReq.getInsurerId());
			infoVo.setCompanyType(carQuoteReq.getCompanyType());
			infoVo.setQuoteNo(carQuoteReq.getQuoteNo());
		} else {
			qRes.getCarQuoteInfo().setInsurerId(carQuoteReq.getInsurerId() == null ? "" : carQuoteReq.getInsurerId());
			qRes.getCarQuoteInfo().setCompanyType(carQuoteReq.getCompanyType());
			qRes.getCarQuoteInfo().setQuoteNo(carQuoteReq.getQuoteNo());// 报价编号
		}
		
		qRes.setOrderId(carQuoteReq.getOrderId());// 订单编号
		return qRes;
	}
    
	public CarQuoteRes getQuote(RenbaoQuoteContent resContent,Map<String,String> saveParams) {
		CarQuoteRes res = new CarQuoteRes();
		if(resContent==null){
			res.getHeader().setResCode("11000");
			res.getHeader().setResMsg("解析报文失败!");
			return res;
		}else if(resContent.getTotalRecords().equals("0")){
			res.getHeader().setResCode("11000");
			res.getHeader().setResMsg(resContent.getMsg());
			return res;
		}else if(resContent.getTotalRecords().equals("1")&&!"".equals(StringUtil.nullToString(resContent.getData().get(0).getErrMessage()))){
			res.getHeader().setResCode("11000");
			res.getHeader().setResMsg(resContent.getData().get(0).getErrMessage());
			return res;
		}else if(resContent.getData().get(0).getBiInsuredemandVoList()==null||resContent.getData().get(0).getBiInsuredemandVoList().size()!=1){
			res.getHeader().setResCode("11000");
			res.getHeader().setResMsg("报价失败!");
			return res;
		}else if(!"".equals(resContent.getData().get(0).getBiInsuredemandVoList().get(0).getCiInsureDemandDAA().getRemark())){
			res.getHeader().setResCode("11000");
			res.getHeader().setResMsg(resContent.getData().get(0).getBiInsuredemandVoList().get(0).getCiInsureDemandDAA().getRemark());
			return res;
		}else{
			res.setOrderId("");// 为空
			res.getHeader().setResCode("0000");
			res.getHeader().setResMsg("成功!");
			res.setRemark("");
			// 报价失败直接返回
			if ("11000".equals(res.getHeader().getResCode()))
				return res;

			CarQuoteInfoVo info = new CarQuoteInfoVo();
			res.setCarQuoteInfo(info);

			info.setQuoteNo("");// 暂空
			info.setOrderId("");// 暂空
			info.setInsurerName("");// 暂空
			info.setCompanyType("");// 保险公司
			info.setSumStdPrem("");//标准保费
			info.setSumPayTax("");//车船税总缴付税额
			info.setTaxType("");// 可空
			info.setThisPayTax("0");// 可空
			info.setPrePayTax("0");// 可空
			info.setDelayPayTax("0");// 可空
			info.setSumPayablePrem("");//应交保费，不含车船税

			//info.setSumPayAmount();//应付金额，应交保费 + 车船税总额
			//Map<String,String> nameValue = HuaanUtil.getResContentValues(content);
			
			/*info.setThisPayTax(String.valueOf(jqTax));//车船税当年应缴
			info.setSumPayablePrem(String.valueOf(syPremimum+jqPremimum));//应交保费，不含车船税
			info.setSumPayAmount(syPremimum+jqPremimum+jqTax);//应付金额，应交保费 + 车船税总额
			info.setSumCiPremium(jqPremimum+jqTax);//交强险应付保费(含税)
			info.setSumBiPremium(syPremimum);//商业险应付保费
			info.setSumInsured(String.valueOf(syInsured+jqInsured));//总保额 = 商业+交强
			info.setActualValue(nameValue.get("Vhl.NActualValue"));// 车辆实际价值
*/			
			List<CarQuoteInsItemVo> insItemList = getCarItemList(resContent,res,saveParams);
			info.setCarQuoteInsItemList(insItemList);//险种明细
		}
		return res;
	}
	/**
	 * @Description: TODO(封装交强险、商业险)
	 * @param resContent 报文对象
	 * @return 险种列表
	 * @author yejie.huang
	 * @date 2016年11月23日 下午4:39:20
	 */
	public List<CarQuoteInsItemVo> getCarItemList(RenbaoQuoteContent resContent,CarQuoteRes res,Map<String,String> saveParams){
		List<PrpCitemKind> cCiBiItems = new ArrayList<PrpCitemKind>();
		CiInsureTax ciInsureTax = null;
		CiInsureDemand ciInsureDemand = null;
		//cCiBiItems.addAll(resContent.getData().get(0).getBiInsuredemandVoList());
		//cCiBiItems.addAll(resContent.getData().get(0).getCiInsureVOList());
		List<RenbaoBiInsure> cBiItems = resContent.getData().get(0).getBiInsuredemandVoList();
		List<RenbaoBiInsure> cCiItems = resContent.getData().get(0).getCiInsureVOList();
		if(cBiItems!=null&&cBiItems.size()==1)
			cCiBiItems.addAll(cBiItems.get(0).getPrpCitemKinds());//交强险
		if(cCiItems!=null&&cCiItems.size()==1){
			cCiBiItems.addAll(cCiItems.get(0).getPrpCitemKinds());//商业险
			ciInsureTax = cCiItems.get(0).getCiInsureTax();
			ciInsureDemand= cCiItems.get(0).getCiInsureDemand();
		}
		return getItems(ciInsureTax,ciInsureDemand,cCiBiItems,res,saveParams);
	}
	/**
	 * @Description: TODO(封装险种)
	 * @param carShipTax 返回报文车船税对象
	 * @param ciInsureDemand 返回报文交强信息，交强特有对象（标准保费、应交保费属性）
	 * @param ciBiItems 商业险、交强险（保额属性）列表
	 * @author yejie.huang
	 * @date 2016年11月24日 上午9:28:15
	 */
	public List<CarQuoteInsItemVo> getItems(CiInsureTax ciInsureTax,CiInsureDemand ciInsureDemand,List<PrpCitemKind> ciBiItems,CarQuoteRes res,Map<String,String> saveParams){
		List<CarQuoteInsItemVo> items = new ArrayList<CarQuoteInsItemVo>();
		BigDecimal sumInsured=new BigDecimal("0"),sumStdPrem=new BigDecimal("0"),biPremium=new BigDecimal("0"),ciPremium=new BigDecimal("0"),ciNetPremium=new BigDecimal("0"),biSumTax=new BigDecimal("0");
		for(PrpCitemKind cItem :ciBiItems){
			CarQuoteInsItemVo item = new CarQuoteInsItemVo();
			String kindCode = (String) RenbaoConfig.getKindCodeMap().get(cItem.getKindCode());
			int category = 1;
			String benchmarkPremium = cItem.getBenchMarkPremium();
			String premium = cItem.getPremium();
			String amount = cItem.getAmount();
			if("10".equals(kindCode)){//交强险从CiInsureDemand对象获取标准保费、应交保费
				category=0;
				benchmarkPremium = ciInsureDemand.getBenchMarkPremium();
				premium = ciInsureDemand.getPremium();
			}else if("4".equals(kindCode)){//乘客座位险保额=座位X单个保额
				 if(ValidatorUtil.isNumeric(amount)&&ValidatorUtil.isNumeric(cItem.getQuantity())){
					 amount = new BigDecimal(amount).multiply(new BigDecimal(cItem.getQuantity())).toString();
				 }else{
					 amount = "0";
				 }
			}
			item.setCategory(category);
			item.setSeatNum(cItem.getQuantity());
			//item.setDeductibleFlag(0);
			item.setKindCode(kindCode);
			item.setKindName(CarInsuranceConstant.getCarInsuranceTypeMap().get(kindCode));
			item.setBenchmarkPremium(benchmarkPremium);
			item.setDiscount(cItem.getDisCount());
			item.setInsuredAmount(amount);
			item.setPremium(premium);
			items.add(item);
			
			/**
			 * 计算总保额、总标准保费、总应交保费，人保系统没有直接返回总和
			 */
			  if(ValidatorUtil.isNumeric(cItem.getAmount()))
				 sumInsured = sumInsured.add(new BigDecimal(amount));
			  if(ValidatorUtil.isNumeric(benchmarkPremium)&&item.getCategory()==1)
				  sumStdPrem = sumStdPrem.add(new BigDecimal(benchmarkPremium));
			  if(ValidatorUtil.isNumeric(cItem.getTaxPremium())&&item.getCategory()==1)
				  biSumTax = biSumTax.add(new BigDecimal(cItem.getTaxPremium()));
			  if(ValidatorUtil.isNumeric(cItem.getNetPremium())&&item.getCategory()==1)
				  ciNetPremium = ciNetPremium.add(new BigDecimal(cItem.getNetPremium()));
			  if(ValidatorUtil.isNumeric(premium)&&item.getCategory()==1){
				  biPremium = biPremium.add(new BigDecimal(premium));
			  }else if(ValidatorUtil.isNumeric(premium)&&item.getCategory()==0){
				  ciPremium = ciPremium.add(new BigDecimal(premium));
			  }
		}
	  saveParams.put("prpCmain.sumTaxPremium",String.valueOf(biSumTax));//商业险总税额
	  saveParams.put("prpCmain.sumPremium",String.valueOf(biPremium));//商业险总应交保费
	  saveParams.put("prpCmain.sumNetPremium",String.valueOf(ciNetPremium));//商业险总净保费
	  res.getCarQuoteInfo().setSumInsured(String.valueOf(sumInsured));
	  res.getCarQuoteInfo().setSumStdPrem(String.valueOf(sumStdPrem));
	  res.getCarQuoteInfo().setSumBiPremium(biPremium.doubleValue());
	  res.getCarQuoteInfo().setSumCiPremium(ciPremium.doubleValue());
	  //res.getCarQuoteInfo().setSumPayTax(String.valueOf(sumTax));
	  BigDecimal sumPayAmount = biPremium.add(ciPremium);
	  res.getCarQuoteInfo().setSumPayAmount(sumPayAmount.doubleValue());
	  if(ciInsureTax!=null){//车船税
		  res.getCarQuoteInfo().setThisPayTax(ciInsureTax.getAnnualTaxDue());
		  res.getCarQuoteInfo().setDelayPayTax("");
		  res.getCarQuoteInfo().setPrePayTax("");
		  res.getCarQuoteInfo().setSumPayTax(ciInsureTax.getSumTax());
	  }
	  System.out.println("saveParams -->"+saveParams);
	  return items;
	}
}
