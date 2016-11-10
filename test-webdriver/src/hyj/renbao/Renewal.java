/**
 * 
 */
package hyj.renbao;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.ebtins.dto.open.CarAssuredInfoDTO;
import com.ebtins.dto.open.CarInsurerInfoDTO;
import com.ebtins.dto.open.CarLicenseInfoVo;
import com.ebtins.dto.open.CarOwnerInfoVo;
import com.ebtins.dto.open.CarQuoteInfoVo;
import com.ebtins.dto.open.CarQuoteInsItemVo;
import com.ebtins.dto.open.CarRenewalReq;
import com.ebtins.dto.open.CarRenewalRes;

import ebtins.smart.proxy.company.renbao.dto.RenbaoRenewalContent;
import ebtins.smart.proxy.company.renbao.dto.RenbaoRenewalData;
import ebtins.smart.proxy.company.renbao.util.RenbaoUtil;
import huaan.quote.util.StringUtil;
import hyj.login.RenBaoLoginA;

/**
 * @ClassName: Renewal
 * @Description: TODO()
 * @author yejie.huang
 * @date 2016年11月7日 下午2:05:18
 *
 */

public class Renewal {
  public static CarRenewalRes getRenewal(CarRenewalReq req) throws Exception{
	  RenBaoLoginA rl = new RenBaoLoginA();
	  String cookie = rl.login();
	  String queryRenewalUrl = "http://10.134.130.208:8000/prpall/business/selectRenewal.do?pageSize=10&pageNo=1";
	  String referRenewalUrl = "http://10.134.130.208:8000/prpall/business/prepareRenewal.do?bizType=PROPOSAL&editType=RENEWAL";
	  Map<String, String> params=new HashMap<String, String>();
	  params.put("prpCrenewalVo.engineNo","");
	  params.put("prpCrenewalVo.frameNo","");
	  params.put("prpCrenewalVo.licenseNo",req.getLicenseNo());
	  params.put("prpCrenewalVo.licenseType","02");
	  params.put("prpCrenewalVo.vinNo", "");
	  params.put("prpCrenewalVo.policyNo", "");
	  params.put("riskCode", "DAA");
	  String body =RenBaoLoginA.post(queryRenewalUrl, params,"GBK",cookie,referRenewalUrl);
	  System.out.println("bbb__>"+body);
	  RenbaoRenewalContent renewalContent = JSON.parseObject(body, RenbaoRenewalContent.class);
	  String BIpolicyNo = "",CIpolicyNo =	"";
	  for(RenbaoRenewalData data :renewalContent.getData()){
		  if(data.getLastDamagedBI()!=null){
			  BIpolicyNo = data.getPolicyNo();
		  }else if(data.getLastDamagedCI()!=null){
			  CIpolicyNo = data.getPolicyNo();
		  }
			  
	  }
	  CarRenewalRes res= new CarRenewalRes();
	  CarQuoteInfoVo carQuoteInfo = new CarQuoteInfoVo();
	  List<CarQuoteInsItemVo> items = new ArrayList<CarQuoteInsItemVo>(); 
	  carQuoteInfo.setCarQuoteInsItemList(items);
	  res.setLastCarQuoteInfo(carQuoteInfo);
	  reqRenewal(res,cookie,CIpolicyNo,0);
	  reqRenewal(res,cookie,BIpolicyNo,1);
	  return res;
  }
  public static CarRenewalRes reqRenewal(CarRenewalRes res,String cookie,String policyNo,int category) throws Exception{
	  String UTF8 = "utf-8";
	  Map<String, String> outCookies =new HashMap<String, String>();
	  String renewalUrl = "http://10.134.130.208:8000/prpall/business/browsePolicyNo.do?bizNo="+policyNo;
	  String renewalBody =RenBaoLoginA.get(renewalUrl,UTF8,cookie,null,outCookies);
	  
	  //商业险
	  String action = category==1?"showCitemKind.do":"showCitemKindCI.do";
	  String itemKindUrl = "http://10.134.130.208:8000/prpall/business/"+action+"?editType=SHOW_POLICY&bizType=POLICY&bizNo="+policyNo+"&riskCode=DAA&minusFlag=&contractNo=%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20&comCode=44030716&originQuery=&proposalNo=TDAA201544030000056456&rnd614=Mon%20Nov%207%2013:44:34%20UTC+0800%202016";
	  String itemKinddbody =RenBaoLoginA.get(itemKindUrl,UTF8,cookie,renewalUrl,outCookies);
	  System.out.println("itemKinddbody-->"+itemKinddbody);
	  
	  if(category==1){
		  getCarQuoteInfoBI(res.getLastCarQuoteInfo(),itemKinddbody);
		  getBIRenewal(res,renewalBody);
		  res.getLastCarQuoteInfo().getCarQuoteInsItemList().addAll(getkindItemsBI(itemKinddbody));
	  }else if(category==0){
		  getCarQuoteInfoCI(res.getLastCarQuoteInfo(),itemKinddbody);
		  getCIRenewal(res,renewalBody);
		  res.getLastCarQuoteInfo().getCarQuoteInsItemList().add(getkindItemCI(itemKinddbody));
		  
	  }
	  
	  if(res.getCarLicenseInfo()==null){
		  //驶证及相关行驶信息
		  String carUrl = "http://10.134.130.208:8000/prpall/business/showCitemCar.do?editType=SHOW_POLICY&bizType=POLICY&bizNo="+policyNo+"&riskCode=DAA&minusFlag=&contractNo=%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20&comCode=44030716&originQuery=&proposalNo=TDAA201544030000056456&rnd268=Mon%20Nov%207%2013:44:21%20UTC+0800%202016";
		  String carbody =RenBaoLoginA.get(carUrl,UTF8,cookie,renewalUrl,outCookies);
		  CarLicenseInfoVo carLicenseInfo = getCar(carbody);
		  res.setCarLicenseInfo(carLicenseInfo);
		  
		  //投保人、被保人、车主
		  String insureUrl = "http://10.134.130.208:8000/prpall/business/showCinsured.do?editType=SHOW_POLICY&bizType=POLICY&bizNo="+policyNo+"&riskCode=DAA&minusFlag=&contractNo=%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20&comCode=44030716&originQuery=&proposalNo=TDAA201544030000056456&rnd215=Mon%20Nov%207%2013:44:25%20UTC+0800%202016";
		  String nsurebody =RenBaoLoginA.get(insureUrl,UTF8,cookie,renewalUrl,outCookies);
		  getInsureAndOwner(nsurebody,res);
	  }
	  return res;
  }
  
  public static CarQuoteInfoVo getCarQuoteInfoBI(CarQuoteInfoVo carQuoteInfo,String body) throws Exception{
	  Map<String,String> relateMap = new HashMap<String,String>();
	  relateMap.put("quoteNo", "");//报价单号
	  relateMap.put("orderId", "");//车险订单Id
	  relateMap.put("insurerId", "");//保险公司id
	  relateMap.put("insurerName", "");//保险公司名称
	  relateMap.put("companyType", "");//保险公司类型
	  relateMap.put("quoteStatus", "");//报价状态
	  relateMap.put("sumInsured", "");//总保额
	  relateMap.put("sumStdPrem", "sumBenchPremium");//标准保费
	  relateMap.put("sumPayablePrem", "");//应交保费，不含车船税
	  relateMap.put("sumPayAmount", "");//应付金额，应交保费 + 车船税总额
	  relateMap.put("discount", "prpCmain.discount");//折扣
	  relateMap.put("sumBiPremium", "prpCmain.sumPremium");//商业险应付保费
	  relateMap.put("actualValue", "");//车辆实际价值
	  relateMap.put("remark", "");//备注说明
	  setValueByClazz(carQuoteInfo,body,relateMap);
	  return carQuoteInfo;
  }
  public static CarQuoteInfoVo getCarQuoteInfoCI(CarQuoteInfoVo carQuoteInfo,String body) throws Exception{
	  Map<String,String> relateMap = new HashMap<String,String>();
	  relateMap.put("sumCiPremium", "prpCitemKindCI.premium");//交强险应付保费  
	  relateMap.put("thisPayTax", "prpCcarShipTax.thisPayTax");//车船税当年应缴
	  relateMap.put("prePayTax", "prpCcarShipTax.prePayTax");//车船税往年补缴
	  relateMap.put("delayPayTax", "prpCcarShipTax.delayPayTax");//车船税滞纳金
	  relateMap.put("sumPayTax", "prpCcarShipTax.sumPayTax");//车船税总缴付税额
	  setValueByClazz(carQuoteInfo,body,relateMap);
	  return carQuoteInfo;
  }
  
//交强险
  public static CarQuoteInsItemVo getkindItemCI(String body) throws Exception{
	  Map<String,String> relateMap = new HashMap<String,String>();
	  relateMap.put("quoteDetailId", "");//车险报价险种明细编号
	  relateMap.put("quoteNo", "");//报价单号
	  relateMap.put("category", "");//险种类别: 0为交强险，1为商业险
	  relateMap.put("kindCode", "prpCitemKindCI.kindCode");//险种代码
	  relateMap.put("kindName", "prpCitemKindCI.kindName");//险种名称
	  relateMap.put("insuredAmount", "prpCitemKindCI.amount");//保额/限额
	  relateMap.put("deductibleFlag", "");//购买不计免赔特约险标志
	  relateMap.put("benchmarkPremium", "prpCitemKindCI.benchMarkPremium");//标准保费
	  relateMap.put("discount", "prpCitemKindCI.disCount");//折扣率
	  relateMap.put("commission", "");//合作方推广费
	  relateMap.put("premium", "prpCitemKindCI.premium");//应交保费
	  CarQuoteInsItemVo res = new CarQuoteInsItemVo();
	  return (CarQuoteInsItemVo) setValueByClazz(res,body,relateMap);
	  
  }
  
  //商业险列表
  public static List<CarQuoteInsItemVo> getkindItemsBI(String body) throws Exception{
	  List<CarQuoteInsItemVo> items = new ArrayList<CarQuoteInsItemVo>();
	  Document  doc =Jsoup.parse(body);
	  Elements eles = doc.select("input[name~=prpCitemKindsTemp\\[\\d+\\]\\.chooseFlag]");
	  for(Element el :eles){
		  String attrName = el.attr("name").trim();
		  if(el.val().trim().equals("1")&&attrName.contains("[")){
			  String index = attrName.substring(attrName.indexOf("[")+1, attrName.indexOf("]"));//截取中括号内索引号
			  CarQuoteInsItemVo item = getkindItemBI(body,index);
			  items.add(item);
		  }
	  }
	  return items;
  }
 //商业险
  public static CarQuoteInsItemVo getkindItemBI(String body,String index) throws Exception{
	  Map<String,String> relateMap = new HashMap<String,String>();
	  relateMap.put("quoteDetailId", "");//车险报价险种明细编号
	  relateMap.put("quoteNo", "");//报价单号
	  relateMap.put("category", "");//险种类别: 0为交强险，1为商业险
	  relateMap.put("kindCode", "prpCitemKindsTemp["+index+"].kindCode");//险种代码
	  relateMap.put("kindName", "prpCitemKindsTemp["+index+"].kindName");//险种名称
	  relateMap.put("seatNum", "prpCitemKindsTemp["+index+"].quantity");// 司机（乘客）责任险的座位数
	  relateMap.put("insuredAmount", "prpCitemKindsTemp["+index+"].amount");//保额/限额
	  relateMap.put("deductibleFlag", "specialCheckBox["+index+"]");//购买不计免赔特约险标志
	  relateMap.put("benchmarkPremium", "prpCitemKindsTemp["+index+"].benchMarkPremium");//标准保费
	  relateMap.put("discount", "prpCitemKindsTemp["+index+"].disCount");//折扣率
	  relateMap.put("commission", "");//合作方推广费
	  relateMap.put("premium", "prpCitemKindsTemp["+index+"].premium");//应交保费
	  CarQuoteInsItemVo res = new CarQuoteInsItemVo();
	  return (CarQuoteInsItemVo) setValueByClazz(res,body,relateMap);
	  
  }
  //投保人、被保人、车主
  public static void getInsureAndOwner(String body,CarRenewalRes res) throws Exception{
	  Document  doc =Jsoup.parse(body);
	  Elements eles = doc.select("input[name~=prpCinsureds\\[\\d+\\]\\.insuredFlag]");
	  for(Element el :eles){
		  String attrValue = el.attr("value").trim(),attrName = el.attr("name").trim();
		  if(attrName.contains("[")&&attrValue!=null&&attrValue.length()>2){
			  String index = attrName.substring(attrName.indexOf("[")+1, attrName.indexOf("]"));//中括号内索引号
			  if("1".equals(attrValue.substring(0,1)))//投保人
			      res.setCarInsurerInfo(getInsre(body,index));
			  if("1".equals(attrValue.substring(1,2)))//被保人
			     res.setCarAssuredInfo(getAssure(body,index));
			  if("1".equals(attrValue.substring(2,3)))//车主
				  res.setCarOwnerInfo(getCarOwner(body,index));
		  }
	  }
	  
  }
  //投保人
  public static CarInsurerInfoDTO getInsre(String body,String index) throws Exception{
	  Map<String,String> relateMap = new HashMap<String,String>();
	  relateMap.put("insName", "prpCinsureds["+index+"].insuredName");//投保人姓名
	  relateMap.put("insSex", "prpCinsureds["+index+"].sex");//投保人性别
	  relateMap.put("insAge", "prpCinsureds["+index+"].age");// 投保人年龄
	  relateMap.put("insBirthDay", "");//投保人出生日期
	  relateMap.put("insIdType", "prpCinsureds["+index+"].identifyType");//投保人证件类型
	  relateMap.put("insIdNo", "prpCinsureds["+index+"].identifyNumber");//投保人证件号码
	  relateMap.put("insFixedPhone", "prpCinsureds["+index+"].phoneNumber");//投保人固定电话
	  relateMap.put("insMobile", "prpCinsureds["+index+"].mobile");//投保人手机号码
	  relateMap.put("insEmail", "");//投保人邮箱
	  relateMap.put("insLiveAddress", "prpCinsureds["+index+"].insuredAddress");//投保人居住地址
	  relateMap.put("insPostCode", "prpCinsureds["+index+"].postCode");//投保人邮政编码
	  CarInsurerInfoDTO res = new CarInsurerInfoDTO();
	  return (CarInsurerInfoDTO) setValueByClazz(res,body,relateMap);
	  
  }
  //被保人
  public static CarAssuredInfoDTO getAssure(String body,String index) throws Exception{
	  Map<String,String> relateMap = new HashMap<String,String>();
	  relateMap.put("assName", "prpCinsureds["+index+"].insuredName");//被保人姓名
	  relateMap.put("assSex", "prpCinsureds["+index+"].sex");//被保人性别
	  relateMap.put("assAge", "prpCinsureds["+index+"].age");// 被保人年龄
	  relateMap.put("assBirthDay", "");//被保人出生日期
	  relateMap.put("assIdType", "prpCinsureds["+index+"].identifyType");//被保人证件类型
	  relateMap.put("assIdNo", "prpCinsureds["+index+"].identifyNumber");//被保人证件号码
	  relateMap.put("assFixedPhone", "prpCinsureds["+index+"].phoneNumber");//被保人固定电话
	  relateMap.put("assMobile", "prpCinsureds["+index+"].mobile");//被保人手机号码
	  relateMap.put("assEmail", "");//被保人邮箱
	  relateMap.put("assLiveAddress", "prpCinsureds["+index+"].insuredAddress");//被保人居住地址
	  relateMap.put("assPostCode", "prpCinsureds["+index+"].postCode");//被保人邮政编码
	  CarAssuredInfoDTO res = new CarAssuredInfoDTO();
	  return (CarAssuredInfoDTO) setValueByClazz(res,body,relateMap);
	  
  }
  //车主
  public static CarOwnerInfoVo getCarOwner(String body,String index) throws Exception{
		  Map<String,String> relateMap = new HashMap<String,String>();
		  relateMap.put("carOwner", "prpCinsureds["+index+"].insuredName");//车主姓名
		  relateMap.put("ownerPhone", "prpCinsureds["+index+"].mobile");// 车主手机号码
		  relateMap.put("ownerIdType", "prpCinsureds["+index+"].identifyType");//车主证件类型
		  relateMap.put("ownerIdentifyNumber", "prpCinsureds["+index+"].identifyNumber");//车主证件号码
		  relateMap.put("ownerAddr", "prpCinsureds["+index+"].insuredAddress");//车主详细地址信息
		  relateMap.put("ownerSex", "prpCinsureds["+index+"].sex");//车主姓别,1为男，2为女
		  relateMap.put("ownerAge", "prpCinsureds["+index+"].age");//车主年龄
		  relateMap.put("ownerBirthday", "");//车主出生日期，日期格式为yyyy-mm-dd
		  CarOwnerInfoVo res = new CarOwnerInfoVo();
		  return (CarOwnerInfoVo) setValueByClazz(res,body,relateMap);
		  
	  }
  //行驶证及相关行驶信息
  public static CarLicenseInfoVo getCar(String body) throws Exception{
	 /* String reg = "<td[^>]*>(((?!<td)[\\s\\S])*?)</td>\\s*<td((?!<td)[\\s\\S])*?(<(input|select)[\\s\\S]*?name=\"([^\"]+)\"[\\s\\S]*?)</td>";
	  //String reg = "<td[\\s\\S]*?<input[^>]*>";
	  System.out.println("------正则匹配--------");
	  StringUtil.regStrings(body, reg, 0);*/
	  
	  Map<String,String> relateMap = new HashMap<String,String>();
	  relateMap.put("licenseNo", "prpCitemCar.licenseNo");
	  relateMap.put("engineNo", "prpCitemCar.engineNo");//发动机号
	  relateMap.put("vin", "prpCitemCar.vinNo");//车辆识别码VIN，即车架号
	  relateMap.put("brandName", "");// 车型名称(品牌型号)
	  relateMap.put("modelCode", "");//车型代码
	  relateMap.put("enrollDate", "prpCitemCar.enrollDate");//车辆初登日期
	  relateMap.put("chgOwnerFlag", "prpCitemCar.transferVehicleFlag");//过户车标志,0-否；1-是，默认为0
	  relateMap.put("chgOwnerDate", "prpCitemCar.transferDate");//
	  relateMap.put("ownerNature", "");//行驶证车主性质:01-个人;02-单位，默认为01
	  relateMap.put("useNature", "prpCitemCar.useNatureCode");// 车辆使用性质,1运营，2非运营，默认为2
	  relateMap.put("countryNature", "prpCitemCar.countryNature");//国别性质：01-国产、02-进口、03-合资
	  relateMap.put("customerType", "");//所有人性质:： 01-个人，02-机关，03-企业，默认为01
	  relateMap.put("licenseType", "LicenseTypeDes");//号牌种类：01为大型车，02为小型车，16为教练汽车，22为临时行驶车，默认为02
	  relateMap.put("nonLocalFlag", "prpCitemCar.noNlocalFlag");//外地车标志，1-外地车，0-本地车，默认为0
	  relateMap.put("taxType", "");//车船税交税类型  1.缴税   2.减税  3.免税   4.完税，默认为1
	  relateMap.put("loanFlag", "");//是否贷款车: 1，是 0，否，默认为0
	  relateMap.put("rentFlag", "");//是否租赁车: 1，是 0，否，默认为0
	  CarLicenseInfoVo res = new CarLicenseInfoVo();
	  return (CarLicenseInfoVo) setValueByClazz(res,body,relateMap);
  }
  //CarRenewalRes
  public static void getBIRenewal(CarRenewalRes res,String body) throws Exception{
	  Map<String,String> relateMap = new HashMap<String,String>();
	  relateMap.put("startDateBI", "prpCmain.startDate");
	  relateMap.put("endDateBI", "prpCmain.endDate");
	  setValueByClazz(res,body,relateMap);
  }
  //CarRenewalRes
  public static void getCIRenewal(CarRenewalRes res,String body) throws Exception{
	  Map<String,String> relateMap = new HashMap<String,String>();
	  relateMap.put("startDateCI", "prpCmainCI.startDate");
	  relateMap.put("endDateCI", "prpCmainCI.endDate");
	  setValueByClazz(res,body,relateMap);
  }
  /**
   * @Description: TODO(封装对象)
   * @param obj 需要封装的对象
   * @param body 报文（html）
   * @param relateMap Map类型参数,key为内部com.ebtins.dto.open.*的Model属性，Value为外部抓取报文（html）参数名
   * @return 返回封装对象
   * @author yejie.huang
   * @date 2016年11月8日 下午3:28:36
   */
  public static Object setValueByClazz(Object obj,String body,Map<String,String> relateMap) throws Exception{
	  Document  doc =Jsoup.parse(body);
	  /**
	   * 获取html input标签的value值
	   */
	  for(String key:relateMap.keySet()){
		  String inputValue = "";
		  if(!"".equals(relateMap.get(key))){
			  inputValue =doc.select("input[name="+relateMap.get(key)+"]").attr("value");
			  /**
			   * 特殊情况处理，复选框取的是checked值
			   */
			  if(relateMap.get(key).contains("specialCheckBox")&&doc.select("input[name="+relateMap.get(key)+"]").attr("checked").equals("checked")){
				  inputValue = "1";
			  }
		  }
		  relateMap.put(key, inputValue);
	  }
	  RenbaoUtil.setValueByClazz(obj, relateMap);
	  System.out.println("obj---->"+JSON.toJSONString(obj));
	  return obj;
	  
  }
  public static  CarRenewalRes countSum(CarRenewalRes res){
	  double sumInsured=0.0,sumPayAmount=0.0,sumStdPrem=0.0;
	  for(CarQuoteInsItemVo item : res.getLastCarQuoteInfo().getCarQuoteInsItemList()){
		  sumInsured += Double.parseDouble(item.getInsuredAmount());
		  sumPayAmount += Double.parseDouble(item.getPremium());
		  sumStdPrem += Double.parseDouble(item.getBenchmarkPremium());
	  }
	  res.getLastCarQuoteInfo().setSumInsured(String.valueOf(sumInsured));
	  res.getLastCarQuoteInfo().setPayAmount(String.valueOf(sumPayAmount));
	  res.getLastCarQuoteInfo().setSumStdPrem(String.valueOf(sumStdPrem));
	  return res;
  }
}
