# بناء التطبيق من الهاتف

هذا المشروع يمكن بناؤه على GitHub Actions بدون Android Studio على الهاتف.

1. أنشئ مستودعًا جديدًا على GitHub باسم `DiscoverMauritania`.
2. ارفع ملفات هذا المشروع إلى المستودع، مع مجلد `.github/workflows`.
3. افتح تبويب **Actions** ثم شغّل **Build Discover Mauritania**.
4. بعد نجاح العملية افتح نتيجة التشغيل، ثم قسم **Artifacts** وحمّل `discover-mauritania-debug-apk`.
5. فك الضغط عن ملف الـartifact ثم ثبّت APK على هاتف Android.

ملاحظة: هذا APK تجريبي (Debug) وليس نسخة النشر النهائية على Google Play. لا تضع مفاتيح API أو كلمات مرور داخل المستودع.
