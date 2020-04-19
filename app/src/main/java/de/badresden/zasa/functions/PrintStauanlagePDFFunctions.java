package de.badresden.zasa.functions;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import de.badresden.zasa.AttributeDetailed;

public class PrintStauanlagePDFFunctions {
	/**
	 * Request code for creating a PDF document.
	 */
	public static final int CREATE_FILE = 1;
	private static final int A4_WIDTH_72_PPI = 595;
	private static final int A4_HEIGHT_72_PPI = 842;
	private static final int MARGIN_LEFT_RIGHT = A4_WIDTH_72_PPI / 20;
	private static final int MARGIN_TOP = A4_HEIGHT_72_PPI / 10;
	private static final int MARGIN_BOTTOM = A4_HEIGHT_72_PPI / 20;
	private static final int TEXT_SIZE = A4_WIDTH_72_PPI / 45;
	private static final int TEXT_OFFSET = A4_WIDTH_72_PPI/ 119;
	private static final int LINE_HEIGHT = TEXT_SIZE + 2 * TEXT_OFFSET;
	private static final int MARGIN_HEADING = A4_HEIGHT_72_PPI / 20;
	private static final float HEADING_SIZE = TEXT_SIZE * 1.5f;
	private static final String HEADING_TEXT = "Protokoll";
	private static final String TAG = "Print_PDF";
	/**
	 *
	 * @param stauanlageValues value of fields will be printed
	 * @param activity activity where Toast-meassages are displayed und Content Resolver
	 * @param uri path where the pdf Document is stored
	 * @return indicates if printing was successful
	 */
	static public boolean printStauanlage(ArrayList<AttributeDetailed> stauanlageValues, Activity activity, Uri uri){

				// Perform operations on the document using its URI.
				PdfDocument document = createPdfDocument(stauanlageValues);
				try {
					ParcelFileDescriptor pfd = activity.getContentResolver().openFileDescriptor(uri, "w");
					FileOutputStream fileOutputStream = new FileOutputStream(pfd.getFileDescriptor());
					document.writeTo(fileOutputStream);
					fileOutputStream.close();
					pfd.close();
					Toast.makeText(activity, "Done", Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					Log.e("main", "error "+e.toString());
					Toast.makeText(activity, "Something wrong: " + e.toString(),  Toast.LENGTH_LONG).show();
				} catch (Exception e){
					Log.e("main", "error "+e.toString());
					Toast.makeText(activity, "Something wrong: " + e.toString(),  Toast.LENGTH_LONG).show();
				}
				// close the document
				document.close();

				return false;

	}

	/**
	 *
	 * @return document has to be closed!!!
	 * @param stauanlageValues //FIXME Hashmap<String, Object> dann soll im .toString() von Object aufgerufen werden
	 */
	private static PdfDocument createPdfDocument(ArrayList<AttributeDetailed> stauanlageValues) {
		int pageNumber = 1;
		PdfDocument document = new PdfDocument();

		// crate a page description
		PdfDocument.PageInfo pageInfo;
		pageInfo = new PdfDocument.PageInfo.Builder(A4_WIDTH_72_PPI, A4_HEIGHT_72_PPI,pageNumber).create();

		// start a page
		PdfDocument.Page page = document.startPage(pageInfo);

		Canvas canvas = page.getCanvas();
		// heading
		Paint headingPaint = new Paint();
		headingPaint.setColor(Color.BLACK);
		headingPaint.setTextSize(HEADING_SIZE);
		float heading_width = headingPaint.measureText(HEADING_TEXT);
		canvas.drawText("Protokoll",(A4_WIDTH_72_PPI - heading_width) / 2, MARGIN_HEADING, headingPaint);
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(TEXT_SIZE);
		int lineCounter = 0;
		float line_y_pos = calculateLine_y(lineCounter); // y - position of the seperation Line
		float text_y_pos = calculateText_y(line_y_pos); //y - Posion of Text (Bottom line of Text)
		ArrayList<ArrayList<String>> descriptionLines = new ArrayList<>();
		ArrayList<ArrayList<String>> valueLines = new ArrayList<>();
		ArrayList<String> descriptions = new ArrayList<>();
		ArrayList<String> values = new ArrayList<>();
		for(AttributeDetailed attribute: stauanlageValues){
			descriptions.add(attribute.getDescription());
			if (attribute.getValue() != null) {
				values.add(attribute.getValue().toString());
			} else {
				values.add("");
			}
		}
		descriptionLines = convertToLines(descriptions,paint,A4_WIDTH_72_PPI/2 - MARGIN_LEFT_RIGHT * 2);
		valueLines = convertToLines(values,paint,A4_WIDTH_72_PPI/2 - MARGIN_LEFT_RIGHT * 2);
		for(int i = 0; i < descriptionLines.size(); i++) {
			ArrayList<String> description = descriptionLines.get(i);
			ArrayList<String> value = valueLines.get(i);
			Log.d(TAG, "createPdfDocument: " + description);
			Log.d(TAG, "createPdfDocument: " + value);
			if(text_y_pos > A4_HEIGHT_72_PPI - MARGIN_BOTTOM){
				//reset line counter and y - position
				lineCounter = 0;
				line_y_pos = calculateLine_y(lineCounter);
				text_y_pos = calculateText_y(line_y_pos);
				//new page
				document.finishPage(page);
				pageNumber++;
				pageInfo = new PdfDocument.PageInfo.Builder(A4_WIDTH_72_PPI, A4_HEIGHT_72_PPI,pageNumber).create();
				// start a page
				page = document.startPage(pageInfo);
				// draw something on the page
				canvas = page.getCanvas();
			}
			canvas.drawLine(MARGIN_LEFT_RIGHT,line_y_pos,A4_WIDTH_72_PPI - MARGIN_LEFT_RIGHT,line_y_pos, paint);
			int numberOfRows = Math.max(description.size(),value.size());
			for(int j = 0; j < numberOfRows; j++){
				String lineDescription;
				String lineValue;
				if (description.size() > j) {
					lineDescription = description.get(j);
				}else{
					lineDescription = "";
				}
				if (value.size() > j) {
					lineValue = value.get(j);
				} else {
					lineValue = "";
				}
				canvas.drawText(lineDescription, MARGIN_LEFT_RIGHT,text_y_pos,paint);
				canvas.drawText(lineValue,A4_WIDTH_72_PPI - MARGIN_LEFT_RIGHT - paint.measureText(lineValue), text_y_pos, paint);
				lineCounter++;
				line_y_pos = calculateLine_y(lineCounter);
				text_y_pos = calculateText_y(line_y_pos);
			}


		}

		// finish the page
		document.finishPage(page);
		// add more pages...
		return document;
	}

	private static int calculateLine_y(int lineCounter) {
		return MARGIN_TOP + LINE_HEIGHT * lineCounter;
	}

	private static float calculateText_y(float line_y) {
		return line_y + TEXT_OFFSET + TEXT_SIZE;
	}

	/**
	 *
	 * @param pickerInitialUri for making a path suggestion
	 * @param nameDerAnlage for making a name suggestion
	 * @param letzterBearbeitungsZeitpunkt for making a name suggestion
	 * @return
	 */
	public static Intent createOpenFileIntent(Uri pickerInitialUri, String nameDerAnlage, Date letzterBearbeitungsZeitpunkt) {
		if(  nameDerAnlage == null || nameDerAnlage.equals("")) {
			nameDerAnlage = "unbekannt";
		}
		//FIXME vllt sollte man in dem Fall einen Fehler anzeigen
		if(letzterBearbeitungsZeitpunkt == null){
			letzterBearbeitungsZeitpunkt = Calendar.getInstance().getTime();
		}
		String strDate = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT).format(letzterBearbeitungsZeitpunkt);
		String pdfName = "Protokoll_" + nameDerAnlage + "_" + strDate + ".pdf";
		Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType("application/pdf");
		intent.putExtra(Intent.EXTRA_TITLE, pdfName);

		// Optionally, specify a URI for the directory that should be opened in
		// the system file picker when your app creates the document.
		intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);

		return intent;
	}

	/**
	 * Converts a List of Strings into a List of List of String where each String represents a line that doesn't exeeds the max with, an each
	 * List represents an String Element of the List that was given to the method
	 * @param rows
	 * @param paint
	 * @param maxWidth
	 * @return
	 */
	private static ArrayList<ArrayList<String>> convertToLines(ArrayList<String> rows, Paint paint, float maxWidth){
		ArrayList<ArrayList<String>> output = new ArrayList<>();
		for(String row: rows ) {
			output.add(splitRow(row, paint,maxWidth));
		}
		return output;
	}
	private static ArrayList<String> splitRow(String row,Paint paint, float maxWidth){
		String[] splitedRow = row.split(" ");
		ArrayList<String> lines = new ArrayList<>();
		lines.add(""); //first empty line
		int lineNumber = 0;
		for (String word : splitedRow){
			if(paint.measureText(lines.get(lineNumber) + " " + word) > maxWidth){
				lines.add(word); //new Line with word
				lineNumber++;
			}else{
				//add word to current Line
				String line = makeLine(lines, lineNumber, word);
				lines.set(lineNumber,line);
			}
		}
		return lines;
	}

	private static String makeLine(ArrayList<String> lines, int lineNumber, String word) {
		String line = null;
		if (lines.get(lineNumber).equals("")) {
			line = word;
		}else{
			line = lines.get(lineNumber) + " " + word;
		}
		return line;
	}
}
