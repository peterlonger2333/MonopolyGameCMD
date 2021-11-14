package hk.edu.polyu.comp3211.g27.model.serial;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import hk.edu.polyu.comp3211.g27.model.Player;

import java.io.IOException;

public class PlayerKeyDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        // the key is in this format
        // Player{id='p1'} (as returned by toString())

        return new Player(key.substring(
                key.indexOf("'") + 1,
                key.lastIndexOf("'")
        ));
    }
}
