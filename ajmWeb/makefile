all : omarbr

eigen :
	rm -fr /home/sandbox/cpp/judgeII/include/judge2.h
	javah -jni -classpath /home/sandbox/web/judg/bin -o judge2.h com.judg.service.EigenFaceRecognizer
	mv judge2.h /home/sandbox/cpp/judgeII/include/
	
fisher :
	rm -fr /home/sandbox/cpp/judgeII/include/judge3.h
	javah -jni -classpath /home/sandbox/web/judg/bin -o judge3.h com.judg.service.FisherFaceRecognizer
	mv judge3.h /home/sandbox/cpp/judgeII/include/
	
lbp :
	rm -fr /home/sandbox/cpp/judgeII/include/judge4.h
	javah -jni -classpath /home/sandbox/web/judg/bin -o judge4.h com.judg.service.LBPHFaceRecognizer
	mv judge4.h /home/sandbox/cpp/judgeII/include/

judge :	
	rm -fr /home/sandbox/cpp/judgeII/include/jhelper.h
	javah -jni -classpath /home/sandbox/web/judg/bin -o jhelper.h com.judg.service.NativeService
	mv jhelper.h /home/sandbox/cpp/judgeII/include/
	
omar :
	rm -fr /home/viji/cspace/omar/omarface/jhelper.h
	javah -jni -classpath /home/viji/workspace/omarWeb/build/classes -o jhelper.h com.omar.hubino.services.NativeService
	mv jhelper.h /home/viji/cspace/omar/omarface/
	
omarbr :
	rm -fr /home/viji/cspace/omar/brnative/brjniajm/brjniajm/br_jni_plugin.h
	javah -jni -classpath /home/viji/workspace/ajmWeb/build/classes -o br_jni_plugin.h com.omar.hubino.services.OperBrNativePlugin
	mv br_jni_plugin.h /home/viji/cspace/omar/brnative/brjniajm/brjniajm