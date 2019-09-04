package com.feng.fundation.service.work;

import com.feng.fundation.mod.Work;
import com.feng.fundation.mod.annonation.AvailableWork;
import com.feng.fundation.util.SpringClassScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Feng
 */
@Component
public class WorkService {
    private SpringClassScanner springClassScanner = new SpringClassScanner();

    public Set<Work> getWorks() throws ClassNotFoundException {
        Set<BeanDefinition> beanDefinitions = springClassScanner.include(new AnnotationTypeFilter(AvailableWork.class)).find("com");
        Set<Work> works = new HashSet<>();
        for(BeanDefinition beanDefinition:beanDefinitions){
            Class workClass = Class.forName(beanDefinition.getBeanClassName());
            AvailableWork availableWork = (AvailableWork) workClass.getAnnotation(AvailableWork.class);
            works.add(new Work(availableWork.name(),beanDefinition.getBeanClassName(),availableWork.description()));
        }
        return works;
    }

}
