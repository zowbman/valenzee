package cn.ac.bigo.base.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zwb on 2016/12/5.
 */
public class CustomPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static Map<String, String> ctxPropertiesMap = new HashMap<String, String>();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value.trim());
        }
    }

    public static String getStringValue(String key) {
        return ctxPropertiesMap.get(key);
    }

    public static Integer getIntValue(String key) {
        return Integer.valueOf(ctxPropertiesMap.get(key));
    }

    public static Long getLongValue(String key) {
        return Long.valueOf(ctxPropertiesMap.get(key));
    }
}
