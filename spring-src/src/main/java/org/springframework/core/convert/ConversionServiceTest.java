package org.springframework.core.convert;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author: leigang
 * @version: 2019-01-13
 */
public class ConversionServiceTest {

    private ConversionService conversionService;

    @Before
    public void before() {
        conversionService = new DefaultConversionService();
    }

    // 测试 Converter
    @Test
    public void converterTest() {
        // ObjectToStringConverter
        Assert.assertEquals("false",
                conversionService.convert(false, String.class));
        // StringToBooleanConverter
        Assert.assertTrue(conversionService.convert("true", Boolean.class));
    }

    // 测试 ConverterFactory StringToNumberConverterFactory
    @Test
    public void converterFactoryTest() {
        Assert.assertTrue(conversionService.convert("1.2", double.class) == 1.2d);
        Assert.assertTrue(conversionService.convert("1", int.class) == 1);
        Assert.assertTrue(conversionService.convert("0x10", byte.class) == 0x10);
    }

    // 测试 GenericConverter CollectionToCollectionConverter
    @Test
    public void genericConverterTest() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Set<String> set = (Set<String>) conversionService.convert(list,
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Integer.class)),
                TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(String.class)));
        Assert.assertEquals("1", set.toArray(new String[0])[0]);
    }
}
