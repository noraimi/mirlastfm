package jku.ss09.mir.lastfmecho.main;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import comirva.util.external.URLRetriever;

import jku.ss09.mir.lastfmecho.bo.LastFMParser;
import jku.ss09.mir.lastfmecho.bo.MusicFileParser;
import jku.ss09.mir.lastfmecho.bo.MirArtist;
import jku.ss09.mir.lastfmecho.bo.MirGenre;
import jku.ss09.mir.lastfmecho.bo.feature.Feature;
import jku.ss09.mir.lastfmecho.bo.feature.FeatureFactory;
import jku.ss09.mir.lastfmecho.bo.feature.TagCloudFeature;
import jku.ss09.mir.lastfmecho.bo.similarity.ConsineSimilarityLastFMTagCloud;
import jku.ss09.mir.lastfmecho.comirva.utils.GoogleUrlRetriever;
import jku.ss09.mir.lastfmecho.comirva.utils.TermProfileUtils;
import jku.ss09.mir.lastfmecho.utils.HTMLFileFilter;
import jku.ss09.mir.lastfmecho.utils.UrlDownloader;

import net.roarsoftware.lastfm.Album;
import net.roarsoftware.lastfm.Artist;
import net.roarsoftware.lastfm.Authenticator;
import net.roarsoftware.lastfm.Chart;
import net.roarsoftware.lastfm.Event;
import net.roarsoftware.lastfm.Session;
import net.roarsoftware.lastfm.User;

public class AppMainJakob {


	/**
	 * @param args
	 */
	public static void main(String[] args) {


		
		String dirPath = System.getProperty("user.dir");
		System.out.println(dirPath);
		
		/**
		 * MIR MusicFileParser 
		 * fetches content from genre, artist files provided for the project
		 */
		MusicFileParser fileParser = new MusicFileParser();
		fileParser.run();

//		System.out.println("Genres: " + fileParser.getGenreSet().size());
//		for (MirGenre mirGenre : fileParser.getGenreSet()) {
//			System.out.println(mirGenre.getName());
////			for (MirArtist artist: mirGenre.getArtistList())
////				System.out.println("\t" + artist.getName());
//		}

		/**
		 * LastFMParser
		 * An auxiliary class for fetching content from LastFM via 
		 * User: Jakob Doppler Profile and API Key   
		 */   
//		LastFMParser lastFmParser = new LastFMParser();
//		String artistString= "Incubus";
//		TestLastFMFetching.testExtractArtistInfo(artistString);
//		
		/**
		 * Google Parser
		 * maybe get Information from Google Seach Results - With wget/CoMIRVA
		 * ATTENTION: Google Request for URL RETRIEVING NEEDS ONLY BE DONE ONCE !!! 
		 */
		

//		List<String> artistStringList = new ArrayList<String>();
//		List<MirArtist> artistList = fileParser.getArtistList();
//		for (MirArtist mirArtist : artistList) {
//			artistStringList.add(mirArtist.getName());
//		}
//		//retrieve URLS in 
//		GoogleUrlRetriever retriever = new GoogleUrlRetriever();
//		retriever.run(0,artistStringList);
		
		
		/**
		 *  Url Downloader
		 *  Fetches n files
		 */
//		UrlDownloader urldownloader = new UrlDownloader();
//		//urldownloader.runFile(downloadPath + "urls.dat" ,downloadPath);
//		urldownloader.setMaxPages(50);
//		urldownloader.runDirectory(new File(dirPath + "/data/download/"));
		
		/**
		 * Term Extractor 
		 */
		
		
//		File dir = new File(dirPath + "/data/download/" + "2Pac");
//		Vector<String> terms = TermProfileUtils.extractTermsFromDocuments(dir, new HTMLFileFilter(), null);
//		
//		int x = 1;
//		

		
		/**
		 * Test Calc and Extract Tag Cloud for Every Artist
		 * 
		 */
		
		System.out.println("---------- LastFM Tag Cloud Feature Retrieval ----------");
		List<MirArtist> artistList = fileParser.getArtistList();
		int idx = 1;
		for (MirArtist mirArtist : artistList) {
			// 1. this creates and calculates the feature and 
			// 2. adds it to the mirArtist
			Feature feature = FeatureFactory.createFeatureForArtist(FeatureFactory.FEATURE_TAGCLOUD, mirArtist);
			//Todo exception handlingm if a feature cant be created
			
			mirArtist.addFeature(feature);
			System.out.println(idx + " Artist: " + mirArtist.getName() + "  calcFeature  " + feature.getName());
			idx++;
		}
		
		//calc similarity Matrix - based on tags
		ConsineSimilarityLastFMTagCloud cosinSimilarity = new ConsineSimilarityLastFMTagCloud(1,"CosineSimilarity",artistList);

		if (cosinSimilarity.runCalc() == true) {
			double[][] res = cosinSimilarity.getResults();
			for (int i = 0; i < res.length; i++) {
				String line = "";
				for (int j = 0; j < res[i].length; j++) {
					line+= i + " :"+ res[i][j] +"\t";
				}
				System.out.println(line);
			}
		}
		
		
		
		
		
		
		
//		//test
//		MirArtist testArtist = artistList.get(10);
//		System.out.println("Test For Artist" + testArtist.getName());
//		TagCloudFeature feature = testArtist.getTagCloudFeature();
//		for (String tag : feature.getTopTags().keySet()) {
//			System.out.println("\t" +tag + " \t\tcount: " + feature.getTopTags().get(tag) + " \t\tnorm: " + feature.getTopTagsNorm().get(tag));
//			
//		}
		

	}

}
