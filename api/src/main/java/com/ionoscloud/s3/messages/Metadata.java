

package com.ionoscloud.s3.messages;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/** XML friendly map denotes metadata. */
@Root(name = "Metadata")
@Convert(Metadata.MetadataConverter.class)
public class Metadata {
  Map<String, String> map;

  public Metadata() {}

  public Metadata(Map<String, String> map) {
    this.map = Collections.unmodifiableMap(map);
  }

  public Map<String, String> get() {
    return map;
  }

  /** XML converter class. */
  public static class MetadataConverter implements Converter<Metadata> {
    @Override
    public Metadata read(InputNode node) throws Exception {
      Map<String, String> map = new HashMap<>();
      while (true) {
        InputNode childNode = node.getNext();
        if (childNode == null) {
          break;
        }

        map.put(childNode.getName(), childNode.getValue());
      }

      if (map.size() > 0) {
        return new Metadata(map);
      }

      return null;
    }

    @Override
    public void write(OutputNode node, Metadata metadata) throws Exception {
      for (Map.Entry<String, String> entry : metadata.get().entrySet()) {
        OutputNode childNode = node.getChild(entry.getKey());
        childNode.setValue(entry.getValue());
      }

      node.commit();
    }
  }
}
