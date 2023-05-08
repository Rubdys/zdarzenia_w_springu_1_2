package com.example.zdarzenia_w_springu_1_2.converter;

import com.example.zdarzenia_w_springu_1_2.helper.Caster;
import com.example.zdarzenia_w_springu_1_2.model.annotations.Convertable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class CustomConverter implements HttpMessageConverter<Object> {
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return clazz.isAnnotationPresent(Convertable.class) &&
                mediaType.getSubtype().equals("plain") && mediaType.getType().equals("text");
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.isAnnotationPresent(Convertable.class) &&
                mediaType.getSubtype().equals("plain") && mediaType.getType().equals("text");
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return List.of(MediaType.ALL);
    }

    @Override
    public Object read(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String valueOfMessage = messageToString(inputMessage);
        LinkedHashMap<Field, String> fields = getFieldsFromMessage(valueOfMessage, clazz);
        return createClassObject(clazz, fields);
    }

    @Override
    public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }

    private String messageToString(HttpInputMessage inputMessage) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Reader reader = new BufferedReader(
                new InputStreamReader(
                        inputMessage.getBody(),
                        StandardCharsets.UTF_8
                ))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                builder.append((char) c);
            }
        }

        return builder.toString();
    }

    private LinkedHashMap<Field, String> getFieldsFromMessage(String valueOfInputMessage, Class<?> clazz) throws HttpMessageNotWritableException {
        List<String> fieldsFromMessage = List.of(valueOfInputMessage.split("\\|"));
        List<Field> fieldsFromClass = getFieldsFromClass(clazz);
        return createFieldsMap(fieldsFromMessage, fieldsFromClass);
    }

    private Object createClassObject(Class<?> clazz, Map<Field, String> fields) {
        Constructor<?> constructor = getConstructorFromClass(clazz, fields);
        System.out.println("Constructor: " + constructor);
        System.out.println("Fields: " + fields);
        System.out.println(Arrays.toString(fields.values().toArray()));
        Object[] args = fillArgs(fields);

        try {
            return constructor.newInstance(args);
        } catch (Exception e){
            System.out.println(e);
        }
        throw new HttpMessageNotWritableException("Not working");
    }

    private List<Field> getFieldsFromClass(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        List<Method> getMethodsInClass = Arrays.stream(clazz.getMethods())
                .filter(m -> m.getName().startsWith("get"))
                .collect(Collectors.toList());
        List<String> presumedFieldsBasedOnGetMethods = getPresumedFields(getMethodsInClass);

        for (String presumedField : presumedFieldsBasedOnGetMethods) {
            try {
                Field field = clazz.getDeclaredField(presumedField);
                fields.add(field);
            } catch (NoSuchFieldException e) {
                log.error("No such field as {}", presumedField);
            }
        }
        return fields;
    }

    private LinkedHashMap<Field, String> createFieldsMap(List<String> fieldsFromMessage, List<Field> fieldsFromClass) throws HttpMessageNotWritableException {
        LinkedHashMap<Field, String> fields = new LinkedHashMap<>();
        for (String individualField : fieldsFromMessage) {
            String fieldName = getFieldName(individualField);
            String fieldValue = getFieldValue(individualField);
            Field field = getField(fieldName, fieldsFromClass);
            fields.put(field, fieldValue);
        }
        return fields;
    }

    private Constructor<?> getConstructorFromClass(Class<?> clazz, Map<Field, String> fields) {
        List<Constructor<?>> constructors = List.of(clazz.getConstructors());
        for (Constructor<?> constructor : constructors) {
            if (parametersNumberMatches(constructor.getParameterCount(), fields.size())
                    && parametersTypesMatches(constructor, fields.keySet())) {
                return constructor;
            }
        }
        throw new HttpMessageNotWritableException("No such constructor");
    }

    private Object[] fillArgs(Map<Field, String> fields){
        Object[] args = new Object[fields.size()];
        int i = 0;
        for (Field field : fields.keySet()){
            String value = fields.get(field);
            if(field.getType() != String.class) {
                args[i] = Caster.castStringTo(fields.get(field), field.getType());
            } else {
                args[i] = value;
            }
            i++;
        }
        return args;
    }

    private List<String> getPresumedFields(List<Method> getMethodsInClass) {
        List<String> presumedFields = new ArrayList<>();
        for (Method m : getMethodsInClass) {
            String fieldName = m.getName().substring(3);
            fieldName = Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
            presumedFields.add(fieldName);
        }
        return presumedFields;
    }

    private String getFieldName(String individualField) {
        try {
            return individualField.split(":")[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new HttpMessageNotWritableException("Unreadable body");
        }
    }

    private String getFieldValue(String individualField) {
        try {
            return individualField.split(":")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new HttpMessageNotWritableException("Unreadable body");
        }
    }

    private Field getField(String fieldName, List<Field> fieldsFromClass) {
        return fieldsFromClass.stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst()
                .orElseThrow();
    }

    private boolean parametersNumberMatches(int parameters, int parameters2){
        return parameters == parameters2;
    }

    private boolean parametersTypesMatches(Constructor<?> constructor, Set<Field> fields){
        Set<Field> copyOfFields = new HashSet<>(fields);
        List<Class<?>> parametersTypes = List.of(constructor.getParameterTypes());
        for(Class<?> parameterType : parametersTypes){
            boolean fieldsContainParameter = false;
            for(Field field : copyOfFields){
                if(field.getType().equals(parameterType)){
                    copyOfFields.remove(field);
                    fieldsContainParameter = true;
                    break;
                }
            }
            if(!fieldsContainParameter){
                return false;
            }
        }
        return true;
    }
}
