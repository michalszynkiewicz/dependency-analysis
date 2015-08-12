package org.jboss.da.communication.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class GAVIntegrationTest {

    @Test
    public void testGeneratedJson() throws Exception {
        String groupId = createRandomString();
        String artifactId = createRandomString();
        String version = createRandomString();
        GAV gav = new GAV(groupId, artifactId, version);

        String json = convertToJson(gav);

        String expectedJson = "{\"ga\":{\"groupId\":\"" + groupId + "\",\"artifactId\":\""
                + artifactId + "\"},\"version\":\"" + version + "\",\"groupId\":\"" + groupId
                + "\",\"artifactId\":\"" + artifactId + "\"}";
        assertEquals(expectedJson, json);
    }

    private String convertToJson(Object object) throws IOException {
        OutputStream stream = new ByteArrayOutputStream();
        new ObjectMapper().writeValue(stream, object);
        stream.close();
        return stream.toString();
    }

    @Test
    @Ignore
    public void testParsedJson() throws Exception {
        String groupId = createRandomString();
        String artifactId = createRandomString();
        String version = createRandomString();

        String json = "{\"groupId\":\"" + groupId + "\", \"artifactId\":\"" + artifactId
                + "\", \"version\":\"" + version + "\"}";

        GAV gav = convertFromJson(json, GAV.class);

        assertEquals(groupId, gav.getGroupId());
        assertEquals(artifactId, gav.getArtifactId());
        assertEquals(version, gav.getVersion());
    }

    private <T> T convertFromJson(String json, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(json, clazz);
    }

    private String createRandomString() {
        return "" + new Random().nextLong();
    }
}