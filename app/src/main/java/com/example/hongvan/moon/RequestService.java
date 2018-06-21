package com.example.hongvan.moon;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by NZSE
 * Serverzugriff (Dateidownload) als Beispiel f&uuml;r eine Serviceklasse
 */
public class RequestService extends Service {
    /**
     * @param intent - Intent, das beim Aufruf des Service verwendet wurde
     * @return binder - Kommunikationsobjekt zum Service
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * Bezeichner f&uuml;r Bundleparameter: URL
     */
    public static final String REQUESTURL = "NZSE.url";
    /**
     * Bezeichner f&uuml;r Bundleparameter: Id zu Image
     */
    public static final String UNIQUEID = "NZSE.uniqueId";
    /**
     * Bezeichner f&uuml;r Bundleparameter: Dateiname
     */
    public static final String FILENAME = "NZSE.filename";
    /**
     * Bezeichner f&uuml;r Bundleparameter: Verzeichnispfad
     */
    public static final String FILEPATH = "NZSE.filepath";
    /**
     * Bezeichner f&uuml;r Bundleparameter:
     * Activity.RESULT_OK oder Activity.RESULT_CANCEL
     */
    public static final String RESULT = "NZSE.result";
    /**
     * Bezeichner f&uuml;r Bundleparameter: kurze Benachrichtigung
     */
    public static final String NOTIFICATION = "NZSE.notification";

    private Handler uiServiceCallbackHandler;
    //----------------------------

    /**
     * Binder f&uuml;r den Client
     */
    private final IBinder mBinder = new RequestServiceBinder();

    /**
     * spezifische Merkmale (Beispiel)
     */
    public String prettyAttribute = "Pretty Request Service";

    /**
     * Klasse RequestServiceBinder f&uuml;r den Client.
     * Dieser Service l&auml;uft im selben Process ab wie die Client Activity;
     * daher wird kein weiter Kommunikationsaufwand (IPC) benf&ouml;tigt
     */
    public class RequestServiceBinder extends Binder {
        /**
         * @return liefert Instanz, damit der Client die public Methoden nutzen kann
         */
        RequestService getService() {
            return RequestService.this;
        }
    } // RequestServiceBinder

    /**
     * Methode wird vom System/android aufgerufen,
     * wenn ein Service(mit startService) gestartet wird
     *
     * @param intent  - intent von startService Aufruf
     * @param flags   - flags ist 0 oder eine Kombination aus
     *                START_FLAG_REDELIVERY oder START_FLAG_RETRY
     * @param startId - eindeutiger Id
     * @return Rückgabe ist einer der Werte START_STICKY_COMPATIBILITY,
     * START_STICKY, START_NOT_STICKY oder START_REDELIVER_INTENT
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }


    /**
     * Callback registrieren
     *
     * @param callbackHandler - Handler im Client bereitstellen
     */
    public void setCallback(Handler callbackHandler) {
        uiServiceCallbackHandler = callbackHandler;
    }

    /**
     * Konstruktor
     */
    public RequestService() {
        super();
        Log.i("RequestService", "*** wird erzeugt! ***");
    }

    /**
     * Download als Thread organisieren und starten
     *
     * @param id        eindeutiger Bezeichner
     * @param urlPath  URL
     * @param filePath lokales Verzeichnis (Cache)
     * @param fileName Dateiname (auf dem Server und lokal)
     */
    public void runURLDownload
    (final String id, final String urlPath, final String filePath,
     final String fileName) {
        DownloadThread dThread = new DownloadThread(id, urlPath, filePath, fileName);
        dThread.start();
    }

    /**
     * Callback organisieren
     *
     * @param uniqueId      eindeutiger Bezeichner
     * @param outputPath lokaler Verzeichnispfad
     * @param result     Activity.RESULT_OK oder Activity.RESULT_CANCELED
     */
    private void deliverResults(String uniqueId, String outputPath, int result, int size) {

        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(NOTIFICATION, String.valueOf (size));//"Download erfolgreich beendet.");
        bundle.putString(FILEPATH, outputPath);
        bundle.putString(UNIQUEID, uniqueId);
        bundle.putInt(RESULT, result);
        msg.setData(bundle);
        uiServiceCallbackHandler.sendMessage(msg); // Callback
    }

    /**
     * Organisation des Download Thread
     */
    class DownloadThread extends Thread {
        /**
         * eindeutiger Bezeichner
         */
        private String uniqueId;
        /**
         * URL
         */
        private String urlPath;
        /**
         * Verzeichnis
         */
        private String filePath;
        /**
         * Dateiname
         */
        private String fileName;
        /**
         * Activity.RESULT_OK, Activity.RESULT_CANCELED
         */
        private int result;

        /**
         * Download
         *
         * @param id    - Id zum Image
         * @param urlPath  - URL
         * @param filePath - Verzeichnis
         * @param fileName - Dateiname
         */
        DownloadThread(final String id, final String urlPath, final String filePath, final String fileName) {
            this.result = Activity.RESULT_CANCELED;
            this.uniqueId = id;
            this.urlPath = urlPath;
            this.filePath = filePath;
            this.fileName = fileName;
        }

        /**
         * spezifische run-Methode
         * Verbindung zum Server herstellen, Daten herunterladen und
         * in einer Datei lokal (im Cache) abspeichern
         */
        public void run() {

            try {
                FileOutputStream fos = null;

                //File output = new File(Environment.getExternalStorageDirectory(),
                File output = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                filePath + "/" + fileName);
                if (output.exists()) output.delete();

                InputStream inputStream = null;
                int downloadedSize = 0;
                try {
                    Log.i("FILE READER", "!... some input **********************");
                    Log.i("FILE READER", "!... " + output.getPath());

                    //new URL(urlPath + "/" + fileName);
                    java.net.URL url = new URL(urlPath );
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();

                    inputStream = urlConnection.getInputStream();
                    fos = new FileOutputStream(output.getPath());


                    byte[] buffer = new byte[1024];
                    int bufferLength;
                    while ((bufferLength = inputStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, bufferLength);
                        downloadedSize += bufferLength;
                    }
                    Log.i("Progress: ", "downloadedSize: " + downloadedSize );

                    // successfully finished
                    result = Activity.RESULT_OK;
                } catch (Exception e) {
                    Log.i("Exception:", " URLConnection" );
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            Log.i("Exception:", " InputStream null" );
                            e.printStackTrace();
                        }
                    } // stream
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            Log.i("Exception:", " close fails" );
                            e.printStackTrace();
                        }
                    } // fos
                }// finally

                this.result = Activity.RESULT_OK;
                deliverResults(this.uniqueId, output.getAbsolutePath(), this.result, downloadedSize);

            } catch (Exception e) {
            }
        }// run
    } // DownloadThread
} // RequestService

