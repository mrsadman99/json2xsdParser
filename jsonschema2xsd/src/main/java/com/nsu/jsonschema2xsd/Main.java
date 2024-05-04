package com.nsu.jsonschema2xsd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.ethlo.jsons2xsd.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException, TransformerException {
        try (final Reader r = new BufferedReader(
                new FileReader("/home/mrsadman/Projects/json2xsd/json-schema.json"))) {
            final Config cfg = new Config.Builder()
                    .targetNamespace("http://example.com/myschema.xsd")
                    .name("array")
                    .build();
            final Document doc = Jsons2Xsd.convert(r, cfg);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileWriter("xmlSchema.xsd"));
            transformer.transform(source, result);
        }
    }
}
