package com.hernandazevedo.moviedb.data.util

class NativeUtils : RemoteNativeUtils {
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String
    external fun getOmdbApiKey(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun getApiKey(): String = getOmdbApiKey()
}
