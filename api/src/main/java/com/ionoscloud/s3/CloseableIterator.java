package com.ionoscloud.s3;

import java.io.Closeable;
import java.util.Iterator;

/** Unification of Iterator and Closeable. */
public interface CloseableIterator<T> extends Iterator<T>, Closeable {}
