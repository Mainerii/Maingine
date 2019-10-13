# Maingine 

[![Build Status](https://travis-ci.org/Mainerii/Maingine.svg?branch=master)](https://travis-ci.org/Mainerii/Maingine)

A 3D Game Engine

<h3>Statistics (13.10.2019, version 0.0.3)</h3>

<h4>Maingine</h4>

| Language | Files | Lines    | Blank lines | Comment lines | Code lines |
| -------- | ----- | -------- | ----------- | ------------- | ---------- |
| Java     | 8     | 1246     | 283         | 574           | 389        |
| **SUM**  | **8** | **1246** | **283**     | **574**       | **389**    |

<h4>Tests</h4>

| Language | Files | Lines   | Blank lines | Comment lines | Code lines |
| -------- | ----- | ------- | ----------- | ------------- | ---------- |
| Java     | 1     | 151     | 37          | 0             | 114        |
| **SUM**  | **1** | **151** | **37**      | **0**         | **114**    |

<h4>Example</h4>

| Language | Files | Lines  | Blank lines | Comment lines | Code lines |
| -------- | ----- | ------ | ----------- | ------------- | ---------- |
| Java     | 1     | 30     | 10          | 0             | 20         |
| **SUM**  | **1** | **30** | **10**      | **0**         | **20**     |

<h3>Maingine development</h3>

<h4>Preparation</h4>

Everything is setup for Linux by default. You need to comment out
the Linux lwjglNatives line in <i>build.gradle</i> and uncomment the Windows one.

<h4>Commands</h4>

<b>Build:</b> <code>./gradlew :build</code>.<br>
<b>Test:</b> <code>./gradlew :test</code>.<br>
<b>Check style:</b> <code>./gradlew :checkstyleMain</code>.<br>

Please run the tests before and after making changes.

Tests can be automated by enabling hooks:<br>
<code>git config --local core.hooksPath .githooks/</code>

<h4>Directories</h4>

<b>Sources:</b> <i>src/main/java/</i><br>
<b>Resources:</b> <i>src/main/resources/</i><br>
<b>Builds:</b> <i>build/libs/</i><br>

<h3>Example development</h3>

<h4>Commands</h4>

<b>Build:</b> <code>./gradlew :example:build</code>.<br>
<b>Run:</b> <code>./gradlew :example:run</code>.<br>
<b>Check style:</b> <code>./gradlew :checkstyleTest</code>.<br>

<h4>Directories</h4>

<b>Sources:</b> <i>example/src/main/java/</i><br>
<b>Resources:</b> <i>example/src/main/resources/</i><br>
<b>Builds:</b> <i>example/build/libs/</i><br>
