

package com.ionoscloud.s3.credentials;

/**
 * Definition of credential provider. Provider always gives a valid, non-expired credentials to be
 * used to perform S3 operations. Credential consumer like {@link com.ionoscloud.s3.ApiClient} calls {@link
 * #fetch()} to get credentials. It is provider's responsibility to retrieve credentials upon expiry
 * when {@link #fetch()} is called.
 */
public interface Provider {
  /**
   * Returns a valid {@link Credentials} instance by retrieving from credential provider service if
   * neccessary.
   */
  Credentials fetch();
}
