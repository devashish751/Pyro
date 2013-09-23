package com.mch.payme;

import io.triangle.Session;
import android.os.AsyncTask;


public class Application extends android.app.Application {

	@Override
    public void onCreate()
    {
        super.onCreate();

        // Initialize the Triangle API if it has not been initialized yet
        final Session triangleSession = Session.getInstance();

        if (!triangleSession.isInitialized())
        {
            // Since the initialization performs network IO, we should execute it in a background thread
            AsyncTask<Void, Void, Void> triangleInitializationTask = new AsyncTask<Void, Void, Void>()
            {
                Exception exception;

                @Override
                protected Void doInBackground(Void... voids)
                {
                    // Please note that the keys below will only work in the sample application. Before deploying
                    // to your own environment, please use keys associated with your own developer account
                    try
                    {
                        triangleSession.initialize(
                                "qmSgKaN6TVEswIB", // Application ID
                                "dttAc8m7tQ",      // Access Key
                                "FmoFvRmfoDyNCzLChpVz39nDTf0xHQ6Kkuk8hA2IRJaDxqRUPAQXbUNvYKMgQPxy", // Secret Key
                                Application.this);
                    }
                    catch (Exception exception)
                    {
                        this.exception = exception;
                    }

                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid)
                {
                    super.onPostExecute(aVoid);

                    if (this.exception != null)
                    {
                        // Do error handling if initialization was not successful
                    }
                }
            };

            // Finally execute the task
            triangleInitializationTask.execute();
        }
    }
}
