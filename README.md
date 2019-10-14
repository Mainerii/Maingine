# Maingine 

[![Build Status](https://travis-ci.org/Mainerii/Maingine.svg?branch=master)](https://travis-ci.org/Mainerii/Maingine)

A 3D Game Engine

<h3>Statistics (14.10.2019, version 0.0.4)</h3>

<h4>Maingine</h4>

| Language | Files  | Lines    | Blank lines | Comment lines | Code lines |
| -------- | ------ | -------- | ----------- | ------------- | ---------- |
| Java     | 10     | 1707     | 393         | 773           | 541        |
| **SUM**  | **10** | **1707** | **393**     | **773**       | **541**    |

<h4>Tests</h4>

| Language | Files | Lines   | Blank lines | Comment lines | Code lines |
| -------- | ----- | ------- | ----------- | ------------- | ---------- |
| Java     | 1     | 247     | 61          | 0             | 186        |
| **SUM**  | **1** | **247** | **61**      | **0**         | **186**    |

<h4>Example</h4>

| Language | Files | Lines  | Blank lines | Comment lines | Code lines |
| -------- | ----- | ------ | ----------- | ------------- | ---------- |
| Java     | 1     | 79     | 29          | 0             | 50         |
| **SUM**  | **1** | **79** | **29**      | **0**         | **50**     |

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
