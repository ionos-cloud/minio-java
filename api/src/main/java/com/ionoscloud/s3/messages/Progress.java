

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/** Helper class to denote Progress information of S3 select response message. */
@Root(name = "Progress", strict = false)
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
public class Progress extends Stats {}
