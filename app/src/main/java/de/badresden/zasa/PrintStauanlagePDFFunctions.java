package de.badresden.zasa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PrintStauanlagePDFFunctions {
	/**
	 * Request code for creating a PDF document.
	 */
	public static final int CREATE_FILE = 1;
	public static final int A4_WIDTH_72_PPI = 595;
	public static final int A4_HEIGHT_72_PPI = 842;

	/**
	 *
	 * @param stauanlage value of fields will be printed
	 * @param activity activity where Toast-meassages are displayed und Content Resolver
	 * @param uri path where the pdf Document is stored
	 * @return indicates if printing was successful
	 */
	static public boolean printStauanlage(Stauanlage stauanlage, Activity activity, Uri uri){

				// Perform operations on the document using its URI.
				PdfDocument document = createPdfDocument(stauanlage);
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
	 * @param stauanlage //FIXME Hashmap<String, Object> dann soll im .toString() von Object aufgerufen werden
	 */
	private static PdfDocument createPdfDocument(Stauanlage stauanlage) {
		PdfDocument document = new PdfDocument();

		// crate a page description
		PdfDocument.PageInfo pageInfo;
		pageInfo = new PdfDocument.PageInfo.Builder(A4_WIDTH_72_PPI, A4_HEIGHT_72_PPI,1).create();

		// start a page
		PdfDocument.Page page = document.startPage(pageInfo);

		// draw something on the page
		Canvas canvas = page.getCanvas();
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(12);
		canvas.drawText(stauanlage.nameDerAnlage,10,10,paint);

		// finish the page
		document.finishPage(page);
		// add more pages...
		return document;
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
}
