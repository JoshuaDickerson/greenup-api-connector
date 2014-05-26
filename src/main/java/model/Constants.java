package model;

/**
 * Created by josh on 5/26/14.
 */
public class Constants {
    public enum COMMENT_TYPE{
        ADMIN("ADMIN"),
        MARKER("MARKER"),
        COMMENT("COMMENT");

        private final String value;

        COMMENT_TYPE(String value) {
            this.value = value;
        }
        public String toString(){
            return value;
        }
    }
}
