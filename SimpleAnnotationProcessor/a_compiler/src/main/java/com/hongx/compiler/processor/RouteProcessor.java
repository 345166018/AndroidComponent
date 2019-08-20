package com.hongx.compiler.processor;

import com.google.auto.service.AutoService;
import com.hongx.compiler.annotation.Route;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * @author: fuchenming
 * @create: 2019-08-20 08:28
 */

//@SupportedAnnotationTypes({"com.hongx.compiler.annotation.Route"})

@SupportedSourceVersion(SourceVersion.RELEASE_7)

@AutoService(Processor.class)
public class RouteProcessor extends AbstractProcessor {

    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnv.getFiler();
    }

//    @Override
//    public SourceVersion getSupportedSourceVersion() {
//        return SourceVersion.RELEASE_7;
//    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        //支持的注解
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(Route.class.getCanonicalName());
//        annotations.add("com.hongx.compiler.annotation.Route");
        return annotations;
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        Set<? extends Element> routeElements = roundEnvironment.getElementsAnnotatedWith(Route.class);

        // 遍历所有被注解了@Factory的元素
        for (Element element : routeElements) {

            Route route = element.getAnnotation(Route.class);
            String path = route.path();
            String group = route.group();

            String newClassName = path + "$$Hx";

            StringBuilder builder = new StringBuilder()
                    .append("package com.hongx.processor.auto;\n\n")
                    .append("public class ")
                    .append(newClassName)
                    .append(" {\n\n") // open class
                    .append("\tpublic String getMessage() {\n") // open method
                    .append("\t\treturn \"");

            builder.append(path).append(group).append(" !\\n");


            builder.append("\";\n") // end return
                    .append("\t}\n") // close method
                    .append("}\n"); // close class

            try {
                JavaFileObject source = mFiler.createSourceFile("com.hongx.processor.auto."+newClassName);
                Writer writer = source.openWriter();
                writer.write(builder.toString());
                writer.flush();
                writer.close();
            } catch (IOException e) {

            }
        }

        return true;
    }
}
