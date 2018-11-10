#include <jni.h>
#include <string>
#include "include/native-lib-config.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_hernandazevedo_moviedb_data_util_NativeUtils_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_hernandazevedo_moviedb_data_util_NativeUtils_getOmdbApiKey(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF(OMBD_API_KEY);
}