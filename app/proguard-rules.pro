# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/air/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more text, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-optimizations !field/removal/writeonly,!field/marking/private,!class/merging
#/*,!code/allocation/variable*/
-printmapping mapping.txt
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

# guava
-dontwarn com.google.common.**

-ignorewarnings

# Configuration for Guava 18.0
#
# disagrees with instructions provided by Guava project: https://code.google.com/p/guava-libraries/wiki/UsingProGuardWithGuava

-keep class com.google.common.io.Resources {
    public static <methods>;
}
-keep class com.google.common.collect.Lists {
    public static ** reverse(**);
}
-keep class com.google.common.base.Charsets {
    public static <fields>;
}

-keep class com.google.common.base.Joiner {
    public static com.google.common.base.Joiner on(java.lang.String);
    public ** join(...);
}

-keep class com.google.common.collect.MapMakerInternalMap$ReferenceEntry
-keep class com.google.common.cache.LocalCache$ReferenceEntry

# http://stackoverflow.com/questions/9120338/proguard-configuration-for-guava-with-obfuscation-and-optimization
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe

# Guava 19.0
-dontwarn java.lang.ClassValue
-dontwarn com.google.j2objc.annotations.Weak
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement


# okio
-dontwarn okio.**

# jackson
-dontwarn com.fasterxml.jackson.databind.**
-keepnames class com.fasterxml.jackson.** { *; }
-keepclassmembers public class * { @com.fasterxml.jackson.annotation.JsonCreator *; }
#-keepnames class org.codehaus.jackson.** { *; }
#-keep class org.codehaus.** { *; }

#-keepnames class org.codehaus.jackson.** { *; }

#-dontwarn javax.xml.**
#-dontwarn javax.xml.stream.events.**
#-dontwarn com.fasterxml.jackson.databind.**

# jodatime
-dontwarn org.joda.time.**

# eventbus
-keepattributes *Annotation*,EnclosingMethod
-keepclassmembers class ** { @org.greenrobot.eventbus.Subscribe <methods>; }
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent { <init>(java.lang.Throwable); }

# searchview
-keep class android.support.v7.widget.SearchView { *; }

# wordnik
-keep class com.wordnik.client.** { *; }
-keepclassmembers class com.wordnik.client.** { *; }

# project
-keep class com.livecoinalert.lca.data.model.** { *; }
-keepclassmembers class com.livecoinalert.lca.data.model.** { *; }

-keep class com.livecoinalert.lca.data.manager.** { *; }
-keepclassmembers class com.livecoinalert.lca.data.manager.** { *; }

-keep class com.dreampany.framework.data.model.** { *; }
-keepclassmembers class com.dreampany.framework.data.model.** { *; }

-keep class com.dreampany.framework.data.manager.** { *; }
-keepclassmembers class com.dreampany.framework.data.manager.** { *; }

# material sheet fab
-keep class io.codetail.animation.arcanimator.** { *; }

# rx
-dontwarn rx.internal.util.**
-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-dontnote rx.internal.util.PlatformDependent


# bottombar
-dontwarn com.roughike.bottombar.**

# slf4j
-dontwarn org.slf4j.**
-keep class org.slf4j.** { *; }

# netty
-dontwarn io.netty.**
-keep class io.netty.** { *; }

#facebook rules
# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}

-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**
-dontwarn com.facebook.infer.**

# tools
-keep class cn.trinea.android.** { *; }
-keepclassmembers class cn.trinea.android.** { *; }
-dontwarn cn.trinea.android.**

# bot libre
#-keep class org.botlibre.sdk.LiveChatConnection.** { *; }
-dontwarn org.botlibre.sdk.activity.MainActivity

# auto bahn
-dontwarn de.tavendo.autobahn.**

# apache
-dontwarn org.apache.http.entity.mime.**

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
# support-v7-appcompat
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }
-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}
# support-design
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }
-keep class android.support.design.widget.** { *; }