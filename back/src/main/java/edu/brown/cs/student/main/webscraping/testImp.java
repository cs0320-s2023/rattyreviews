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
            //fails due to ssl errors :( need better certs
            HtmlPage page = webClient.getPage("http://dining.brown.edu/cafe/sharpe-refectory/2023-04-21/");
            System.out.println();
            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();

            System.out.println(page);

            String title = page.getTitleText();
            System.out.println("Page Title: " + title);

            List<?> anchors = page.getByXPath("//a[@class='block group']");
            System.out.println(anchors.size());
            for (int i = 0; i < anchors.size(); i++) {
                System.out.println("fart");
                HtmlAnchor link = (HtmlAnchor) anchors.get(i);
                String recipeTitle = link.getAttribute("title").replace(',', ';');
                System.out.println(recipeTitle);
                String recipeLink = link.getHrefAttribute();
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }




    }
}
