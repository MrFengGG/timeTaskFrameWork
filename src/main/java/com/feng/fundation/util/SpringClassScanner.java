package com.feng.fundation.util;
 
import com.feng.fundation.mod.annonation.AvailableWork;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.Set;

public class SpringClassScanner {

    private ClassPathScanningCandidateComponentProvider classPathScanningCandidateComponentProvider = new ClassPathScanningCandidateComponentProvider(false);

    public SpringClassScanner include(TypeFilter filter){
        classPathScanningCandidateComponentProvider.addIncludeFilter(filter);
        return this;
    }

    public SpringClassScanner exclude(TypeFilter filter){
        classPathScanningCandidateComponentProvider.addExcludeFilter(filter);
        return this;
    }

    public Set<BeanDefinition> find(String scanPackage){
        return this.classPathScanningCandidateComponentProvider.findCandidateComponents(scanPackage);
    }

 
    private ClassPathScanningCandidateComponentProvider createComponentScanner() {
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(AvailableWork.class));
        return provider;
    }

}