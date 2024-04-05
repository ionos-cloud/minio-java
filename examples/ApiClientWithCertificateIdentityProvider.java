

import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.StatObjectArgs;
import com.ionoscloud.s3.StatObjectResponse;
import com.ionoscloud.s3.credentials.CertificateIdentityProvider;
import com.ionoscloud.s3.credentials.Provider;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

public class ApiClientWithCertificateIdentityProvider {
  public static void main(String[] args) throws Exception {
    // STS endpoint usually point to MinIO server.
    String stsEndpoint = "https://STS-HOST:STS-PORT/";

    // SSL socket factory.
    SSLSocketFactory sslSocketFactory = null;

    // Trust manager.
    X509TrustManager trustManager = null;

    // Below is a sample code to construct sslSocketFactory and trustManager for self-signed
    // certificates (server and client) used in a MinIO server setup.
    //
    // CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
    //
    // Certificate serverCertificate = null;
    // try (FileInputStream fis = new FileInputStream("/home/bala/.minio/certs/public.crt")) {
    //   serverCertificate = certificateFactory.generateCertificate(fis);
    // }
    //
    // KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
    // trustStore.load(null, "secret".toCharArray());
    //
    // trustStore.setCertificateEntry("server-certificate", serverCertificate);
    //
    // String privateKeyString =
    //     new String(
    //             Files.readAllBytes(Paths.get("/home/bala/.minio/certs/CAs/client1.key")),
    //             Charset.defaultCharset())
    //         .replace("-----BEGIN PRIVATE KEY-----", "")
    //         .replaceAll(System.lineSeparator(), "")
    //         .replace("-----END PRIVATE KEY-----", "");
    //
    // byte[] privateKey = Base64.getDecoder().decode(privateKeyString);
    // KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    // PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
    //
    // Certificate certificateChain = null;
    // try (FileInputStream fis = new FileInputStream("/home/bala/.minio/certs/CAs/client1.crt")) {
    //   certificateChain = certificateFactory.generateCertificate(fis);
    // }
    //
    // KeyStore identityStore = KeyStore.getInstance(KeyStore.getDefaultType());
    // identityStore.load(null, "secret".toCharArray());
    // identityStore.setKeyEntry(
    //     "client",
    //     keyFactory.generatePrivate(keySpec),
    //     "secret".toCharArray(),
    //     new Certificate[] {certificateChain});
    //
    // TrustManagerFactory trustManagerFactory =
    //     TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    // trustManagerFactory.init(trustStore);
    // TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
    //
    // KeyManagerFactory keyManagerFactory =
    //     KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    // keyManagerFactory.init(identityStore, "secret".toCharArray());
    // KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
    //
    // SSLContext sslContext = SSLContext.getInstance("TLS");
    // sslContext.init(keyManagers, trustManagers, null);
    //
    // SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
    // X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
    //

    Provider provider =
        new CertificateIdentityProvider(stsEndpoint, sslSocketFactory, trustManager, null, null);

    ApiClient apiClient =
        ApiClient.builder()
            .endpoint("https://MINIO-HOST:MINIO-PORT")
            .credentialsProvider(provider)
            .build();

    // Get information of an object.
    StatObjectResponse stat =
        apiClient.statObject(
            StatObjectArgs.builder().bucket("my-bucketname").object("my-objectname").build());
    System.out.println(stat);
  }
}
