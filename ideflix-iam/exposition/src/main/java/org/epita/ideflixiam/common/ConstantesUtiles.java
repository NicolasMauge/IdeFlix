package org.epita.ideflixiam.common;


public final class ConstantesUtiles {

    private static final String ORIGINE_IDEFLIX_APP = "http://localhost:8081";

    private static final String ORIGINE_IDEFLIX_IHM = "http://localhost:4200";

    public static final String[] ORIGINES_IDEFLIX_ARRAY = {ORIGINE_IDEFLIX_IHM, ORIGINE_IDEFLIX_APP};
    //public static final List<String> ORIGINES_IDEFLIX = Collections.unmodifiableList(Arrays.asList(ORIGINE_IDEFLIX_IHM, ORIGINE_IDEFLIX_APP));

    public static final String ORIGINES_IDEFLIX_STRING = "{" + ORIGINE_IDEFLIX_IHM + ", " + ORIGINE_IDEFLIX_APP + "}";
}