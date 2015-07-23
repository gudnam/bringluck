package com.gudnam.bringluck.network;

import java.io.StringReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.SharedPreferences;

public class XmlParsing {
	public final String PARSING_ERROR_UNKNOWN = "error_UnknownHostException";

	public final String PARSING_ERROR_PARSER = "error_XmlPullParserException";

	public final String PARSING_ERROR_EXCEPTION = "error_Exception";

	public final String PARSING_ERROR_UNKNOWN_MSG = "NFE";

	public final String PARSING_ERROR_PARSER_MSG = "PSE";

	public final String PARSING_ERROR_EXCEPTION_MSG = "EX";

	public final String HKEY_BASIC = "basic";

	public XmlParsing() {

	}

	/**
	 * @param editor
	 * @param result
	 */
	public String[] getParsingResultWithTag(String result,
			ArrayList<String> xmlParsingList) {
		XmlPullParserFactory parserCreator = null;
		XmlPullParser parser;
		int tagCount = xmlParsingList.size();
		String[] tagResult = new String[tagCount];
		try {
			parserCreator = XmlPullParserFactory.newInstance();
			parser = parserCreator.newPullParser();
			parser.setInput(new StringReader(result));
			int parserEvent = parser.getEventType();

			while (parserEvent != XmlPullParser.END_DOCUMENT) {
				if (parserEvent == XmlPullParser.START_TAG) {
					for (int i = 0; i < tagCount; i++) {
						if (parser.getName().equals(xmlParsingList.get(i))) {
							parser.next();
							tagResult[i] = parser.getText();
							break;
						}
					}
				}
				parserEvent = parser.next();
			}

		} catch (XmlPullParserException e) {
			tagResult[0] = PARSING_ERROR_PARSER;
			e.printStackTrace();
		} catch (UnknownHostException e) {
			tagResult[0] = PARSING_ERROR_UNKNOWN;
			e.printStackTrace();
		} catch (Exception e) {
			tagResult[0] = PARSING_ERROR_EXCEPTION;
			e.printStackTrace();
		}
		return tagResult;

	}

	public String[] getParsingResultWithTag(String result, String[] tag,
			int valueindex) {
		XmlPullParserFactory parserCreator = null;
		XmlPullParser parser;
		int tagCount = tag.length;
		String[] tagResult = new String[tagCount];
		try {
			parserCreator = XmlPullParserFactory.newInstance();
			parser = parserCreator.newPullParser();
			parser.setInput(new StringReader(result));
			int parserEvent = parser.getEventType();

			while (parserEvent != XmlPullParser.END_DOCUMENT) {
				if (parserEvent == XmlPullParser.START_TAG) {
					for (int i = 0; i < tagCount; i++) {
						if (parser.getName().equals(tag[i])) {
							tagResult[i] = parser.getAttributeValue(valueindex);

							break;
						}
					}
				}
				parserEvent = parser.next();
			}

		} catch (XmlPullParserException e) {
			tagResult[0] = PARSING_ERROR_PARSER;
			e.printStackTrace();
		} catch (UnknownHostException e) {
			tagResult[0] = PARSING_ERROR_UNKNOWN;
			e.printStackTrace();
		} catch (Exception e) {
			tagResult[0] = PARSING_ERROR_EXCEPTION;
			e.printStackTrace();
		}
		return tagResult;

	}

	public String[] getParsingResultWithTagAndUpdate(SharedPreferences pref,
			String result, String[] updateTag, String[] savedName,
			String[] resultTag) {
		XmlPullParserFactory parserCreator = null;
		XmlPullParser parser;
		int resutlCount = resultTag.length;
		int updateCount = updateTag.length;
		String[] tagResult = new String[resutlCount];
		tagResult[0] = "y";
		try {
			parserCreator = XmlPullParserFactory.newInstance();
			parser = parserCreator.newPullParser();
			parser.setInput(new StringReader(result));
			int parserEvent = parser.getEventType();

			while (parserEvent != XmlPullParser.END_DOCUMENT) {
				if (parserEvent == XmlPullParser.START_TAG) {
					for (int i = 0; i < resutlCount; i++) {
						if (parser.getName().equals(resultTag[i])) {
							parser.next();
							tagResult[i] = parser.getText();
							// Log.i("df_parsing","resultName["+i+"]="+tagResult[i]);

							break;
						}
					}
					for (int i = 0; i < updateCount; i++) {
						if (parser.getName().equals(updateTag[i])) {
							parser.next();
							String text = parser.getText();
							if (text != null) {
								SharedPreferences.Editor editor = pref.edit();
								try {
									int itext = Integer.parseInt(text);
									editor.putInt(savedName[i], itext);
								} catch (NumberFormatException e) {
									editor.putString(savedName[i], text);
								}
								editor.commit();

								// Log.i("df_parsing","savedName["+i+"]="+savedName[i]+" updateTag["+i+"]="+updateTag[i]+" text="+text);
							}
							break;
						}
					}
				}
				parserEvent = parser.next();
			}

		} catch (XmlPullParserException e) {
			tagResult[0] = PARSING_ERROR_PARSER;
			e.printStackTrace();
		} catch (UnknownHostException e) {
			tagResult[0] = PARSING_ERROR_UNKNOWN;
			e.printStackTrace();
		} catch (Exception e) {
			tagResult[0] = PARSING_ERROR_EXCEPTION;
			e.printStackTrace();
		}
		return tagResult;

	}

	@SuppressWarnings("unchecked")
	public HashMap<String, ArrayList<String>> getHashMapWithTag(String result,
			String[] tags) {
		HashMap<String, ArrayList<String>> hash_result = new HashMap<String, ArrayList<String>>();

		/*
		 * Object[] obj=new Object[5]; for(int i=0;i<5;i++){ obj[i]=new
		 * ArrayList<String>(); } ((ArrayList<String>)obj[0]).add("hello");
		 * ((ArrayList<String>) obj[0]).add("bye");
		 * Log.i("log","hello="+((ArrayList
		 * <String>)obj[0]).get(0)+" bye="+((ArrayList<String>)obj[0]).get(1));
		 */

		int tagslenght = tags.length;

		Object[] obj = new Object[tagslenght];
		for (int i = 0; i < tagslenght; i++) {
			obj[i] = new ArrayList<String>();
		}

		ArrayList<String> bagic_result = new ArrayList<String>();

		XmlPullParserFactory parser_creator = null;
		XmlPullParser parser;

		try {
			bagic_result.add("y");
			parser_creator = XmlPullParserFactory.newInstance();
			parser = parser_creator.newPullParser();
			parser.setInput(new StringReader(result));
			int parser_event = parser.getEventType();

			while (parser_event != XmlPullParser.END_DOCUMENT) {
				if (parser_event == XmlPullParser.START_TAG) {
					for (int i = 0; i < tagslenght; i++) {
						if (parser.getName().equals(tags[i])) {
							parser.next();
							String text = parser.getText();
							if (text == null) {
								text = "";
							}
							((ArrayList<String>) obj[i]).add(text);

							break;
						}
					}
				}
				parser_event = parser.next();
			}

		} catch (XmlPullParserException e) {
			bagic_result.set(0, PARSING_ERROR_PARSER);
			e.printStackTrace();
		} catch (UnknownHostException e) {
			bagic_result.set(0, PARSING_ERROR_UNKNOWN);
			e.printStackTrace();
		} catch (Exception e) {
			bagic_result.set(0, PARSING_ERROR_EXCEPTION);
			e.printStackTrace();
		}
		hash_result.put(HKEY_BASIC, bagic_result);
		for (int i = 0; i < tagslenght; i++) {
			hash_result.put(tags[i], (ArrayList<String>) obj[i]);
		}
		return hash_result;
	}

}
