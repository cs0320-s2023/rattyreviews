package edu.brown.cs.student.main.webscraping;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class testImp {


    public static void doesItWork() {
//        // Define the search term
//        String searchQuery = "iphone 13";
//
//        // Instantiate the client
//        WebClient client = new WebClient();
//        client.getOptions().setCssEnabled(false);
//        client.getOptions().setJavaScriptEnabled(false);
//
//        // Set up the URL with the search term and send the request
//        String searchUrl = null;
//        try {
//            searchUrl = "https://newyork.craigslist.org/search/moa?query=" + URLEncoder.encode(searchQuery, "UTF-8");
//            HtmlPage page = client.getPage(searchUrl);
//
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        // v Quiets all the exceptions that we don't care about, especially related to ads.
        webClient.getOptions().setCssEnabled(false);
        //webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);

        try {
            /*This might fail due to SSL errors, since the standard installation of Java does not include the InCommon certificate
            * that dining.brown.edu uses. This is normal! You need to install the InCommon cert for your version of Java. This
            * StackOverflow post gives a good guide on how to achieve this: */
            //https://stackoverflow.com/questions/9619030/resolving-javax-net-ssl-sslhandshakeexception-sun-security-validator-validatore
            HtmlPage page = webClient.getPage("http://dining.brown.edu/cafe/sharpe-refectory/2023-04-21/");
            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();

            //System.out.println(page);

            String title = page.getTitleText();
            System.out.println("Page Title: " + title);

            //System.out.println(page.asXml());

            List<HtmlElement> allDayParts = page.getByXPath("//div[@class='c-tab__content-inner site-panel__daypart-tab-content-inner']");
            System.out.println(allDayParts.size());
            List<DomElement> someShit = allDayParts.get(0).getByXPath("child::*");
            System.out.println(someShit.size());


            List<DomElement> breakfastMenu = allDayParts.get(0).getByXPath("child::*");
            List<DomElement> lunchMenu = allDayParts.get(1).getByXPath("child::*");
            List<DomElement> dinnerMenu = allDayParts.get(2).getByXPath("child::*");

            //prints menu
            for (DomElement item : breakfastMenu) {
                System.out.println(item.getTextContent());
            }

            System.out.println();

            List<HtmlButton> anchors = page.getByXPath("//button[@class='h4 site-panel__daypart-item-title']");
            System.out.println(anchors.size());
            for (int i = 0; i < anchors.size(); i++) {
                //System.out.println("fart");
                HtmlButton item = anchors.get(i);
                //System.out.println(item.getTextContent());

//                HtmlAnchor link = (HtmlAnchor) anchors.get(i);
//                String recipeTitle = link.getAttribute("title").replace(',', ';');
//                System.out.println(recipeTitle);
//                String recipeLink = link.getHrefAttribute();
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }




    }
}
