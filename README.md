Bitcoin Commons
===============
![GitHub](https://img.shields.io/github/license/osslabz/bitcoin-commons)
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/osslabz/bitcoin-commons/build-on-push.yml?branch=dev&label=build&logo=git)
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/osslabz/bitcoin-commons/build-release-on-main-push.yml?branch=main&label=perform-release&logo=semanticrelease)
[![Maven Central](https://img.shields.io/maven-central/v/net.osslabz/bitcoin-commons?label=Maven%20Central)](https://search.maven.org/artifact/net.osslabz/bitcoin-commons)

This package decouples my bitcoin related libraries from the underlying implementation (currently [bitcoinj](https://bitcoinj.org/)), although it
seems unlikely this will ever change ;-)

There probably won't be any benefit for anybody to use this directly, it's pulled transitively when required.

0.3.0 is the highest version on Maven Central and was published in March 2025; 0.2.17 is newer by date but sorts lower, which is why GitHub
flags that one as the latest release. Nine releases since June 2023, one test with one assertion, and electrum-client is the only user here.

Current release:

```xml
<dependency>
    <groupId>net.osslabz</groupId>
    <artifactId>bitcoin-commons</artifactId>
    <version>0.3.0</version>
</dependency>
```