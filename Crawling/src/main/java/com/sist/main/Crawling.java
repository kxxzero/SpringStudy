package com.sist.main;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Crawling {

    private static final int MAX_PAGE = 1; // 최대 페이지 수 변경
    private static int totalJobsCrawled = 0;
    private static Set<String> crawledJobUrls = new HashSet<>();

    public static void main(String[] args) {
        for (int i=1; i<=MAX_PAGE; i++) {
            String landingPageUrl = "https://www.work.go.kr/empInfo/empInfoSrch/list/dtlEmpSrchList.do?careerTo=&keywordJobCd=&occupation=133200%7C133201%7C133202%7C133300%7C133301%7C024&templateInfo=&shsyWorkSecd=&rot2WorkYn=&payGbn=&resultCnt=50&keywordJobCont=N&cert=&cloDateStdt=&moreCon=&minPay=&codeDepth2Info=11000&isChkLocCall=&sortFieldInfo=DATE&major=&resrDutyExcYn=&eodwYn=&sortField=DATE&staArea=&sortOrderBy=DESC&keyword=&termSearchGbn=all&carrEssYns=&benefitSrchAndOr=O&disableEmpHopeGbn=&webIsOut=&actServExcYn=&maxPay=&keywordStaAreaNm=N&emailApplyYn=&listCookieInfo=DTL&pageCode=&codeDepth1Info=11000&keywordEtcYn=&publDutyExcYn=&keywordJobCdSeqNo=&exJobsCd=&templateDepthNmInfo=&computerPreferential=&regDateStdt=&employGbn=&empTpGbcd=&region=&infaYn=&resultCntInfo=50&siteClcd=CSI&cloDateEndt=&sortOrderByInfo=DESC&currntPageNo=3&indArea=&careerTypes=&searchOn=Y&tlmgYn=&subEmpHopeYn=&academicGbn=&templateDepthNoInfo=&foriegn=&mealOfferClcd=&station=&moerButtonYn=Y&holidayGbn=&srcKeyword=&enterPriseGbn=01%7C04%7C08%7C06%7C07%7C20%7C05%7C03&academicGbnoEdu=noEdu&cloTermSearchGbn=all&keywordWantedTitle=N&stationNm=&benefitGbn=&keywordFlag=&notSrcKeyword=&essCertChk=&isEmptyHeader=&depth2SelCode=&_csrf=4871e8a6-1790-466b-afad-347ec7c8dd39&keywordBusiNm=N&preferentialGbn=&rot3WorkYn=&pfMatterPreferential=&regDateEndt=&staAreaLineInfo1=11000&staAreaLineInfo2=1&pageIndex=1&termContractMmcnt=&careerFrom=&laborHrShortYn=#viewSPL";
            crawlLandingPage(landingPageUrl);
        }
        System.out.println("총 크롤링된 채용 공고의 수: " + totalJobsCrawled);
    }

    private static void crawlLandingPage(String url) {
        try {
            Document landingPage = Jsoup.connect(url).get();
            Elements jobLinks=landingPage.select("#list1 > td:nth-child(3) > div > div > a");

            for (Element link : jobLinks) {
                String jobDetailUrl = "https://www.work.go.kr" + link.attr("href");
                if (!crawledJobUrls.contains(jobDetailUrl)) {
                    crawlJobDetails(jobDetailUrl);
                    crawledJobUrls.add(jobDetailUrl);
                    totalJobsCrawled++;
                }
            }
        } catch (IOException e) {
            System.err.println("크롤링 중 오류 발생: " + e.getMessage());
        }
    }
    

    private static void crawlJobDetails(String url) {
        try {
            Document document = Jsoup.connect(url).get();

            // 책 정보 추출
            Elements title = document.select("#contents > div.careers-area > div.careers-private > p.title");
//            String author = document.select("meta[name=author]").attr("content");
//            String keywords = document.select("meta[name=keywords]").attr("content");
//            String poster = document.select("meta[property=og:image]").attr("content");
//            String detailedImageURL = document.select("#infoset_chYes > div.infoSetCont_wrap > div > img").attr("data-original");

            // JSON 데이터에서 장르 정보 추출
//            String genre = "";
//            JSONObject jsonLdObject = new JSONObject(document.select("script[type=application/ld+json]").html());
//            if (jsonLdObject.has("genre")) {
//                genre = jsonLdObject.getString("genre");
//            }

            // JSON 데이터에서 출판사 정보 추출
//            String publisher = "";
//            if (jsonLdObject.has("publisher")) {
//                publisher = jsonLdObject.getJSONObject("publisher").getString("name");
//            }

         // 판매 가격 정보 추출
//            String priceString = document.select("input[name=Goods_Price]").attr("value");
//            double priceDouble = Double.parseDouble(priceString);
//            int price = (int) priceDouble;


            // 추출한 정보 출력
            for(int i=0;i<2;i++) {
	            System.out.println("채용 공고 제목: " + title.text());
	//            System.out.println("저자: " + author);
	//            System.out.println("키워드: " + keywords);
	//            System.out.println("장르: " + genre);
	//            System.out.println("출판사: " + publisher);
	//            System.out.println("가격: " + price);
	//            System.out.println("포스터 :"+poster);
	//            System.out.println("상세 이미지 URL: " + detailedImageURL);
	            System.out.println("------------------------");
            }

        } catch (IOException e) {
            System.err.println("상세 정보 크롤링 중 오류 발생: " + e.getMessage());
        }
    }
 
   
}