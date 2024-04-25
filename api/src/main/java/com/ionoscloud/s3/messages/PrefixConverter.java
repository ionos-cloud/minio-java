

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/**
 * XML converter class for prefix due to SimpleXML limitation in converting empty tag
 * <Prefix></Prefix> to empty string.
 */
public class PrefixConverter implements Converter<String> {
  @Override
  public String read(InputNode node) throws Exception {
    String value = node.getValue();
    return value != null ? value : "";
  }

  @Override
  public void write(OutputNode node, String prefix) throws Exception {
    node.setValue(prefix);
  }
}
