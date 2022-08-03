<p align="center">
    <img src="https://raw.githubusercontent.com/wniemiec-mobilex/ama-compiler/master/docs/images/logo/logo.png?raw=true" alt="Logo">
</p>

<h1 align='center'>AMA compiler</h1>
<p align='center'>Mobilang AST to Mobile Application Compiler</p>
<p align="center">
	<a href="https://github.com/wniemiec-mobilex/ama-compiler/actions/workflows/ubuntu.yml"><img src="https://github.com/wniemiec-mobilex/ama-compiler/actions/workflows/ubuntu.yml/badge.svg" alt=""></a>
	<a href="http://node.dev"><img src="https://img.shields.io/badge/NodeJS-8+-D0008F.svg" alt="Node compatibility"></a>
	<a href="http://java.oracle.com"><img src="https://img.shields.io/badge/java-11+-D0008F.svg" alt="Java compatibility"></a>
	<a href="https://github.com/wniemiec-mobilex/ama-compiler/releases"><img src="https://img.shields.io/github/v/release/wniemiec-mobilex/ama-compiler" alt="Release"></a>
	<a href="https://github.com/wniemiec-mobilex/ama-compiler/blob/master/LICENSE"><img src="https://img.shields.io/github/license/wniemiec-mobilex/ama-compiler" alt="License"></a>
</p>	

<hr>

## ❇ Introduction
AST to Mobile Applications compile is a framework-dependent compiler whose objective is to generate mobile applications from an AST generated from a Mobilang file. For this, it must use a cross-platform mobile development (CPMD) framework. However, to use this framework, AMA compiler would need to know how to generate mobile applications from these frameworks. To circumvent this problem, we define an interface that must be implemented if the programmer wants to support a framework that the AMA compiler is unaware of. However, this is our responsibility (to provide compatibility with several CPMD), and from the developer's view, he/she usually does not need to worry about it (only if he/she wants to use a CPMD that AMA compiler does not have compatibility yet). This interface is described below, where "Properties" and "Screen" represent data from tags with the same name in the Mobilang language, while "Project" contains the application project dependencies created by the CPMD framework along with application codes. Finally, it was built with Java and its architecture consists of six modules: models, reader, parser, coder, export, and framework.

#### Interface

```
public interface Framework {
    void createProject(
        Properties properties, Path location
    ) throws IOException;
  
    void addProjectDependency(
        String dependency, Path location
    ) throws IOException;

    Project generateCode(List<Screen> screens) 
    throws CoderException;

    void generateMobileApplicationFor(
        String platform, Path source, Path output
    ) throws AppGenerationException;
}
```

## ❓ How to use

```
Coming soon
```

### Example

```
Coming soon
```

## ✔ Requirements
- [NodeJS](https://nodejs.dev)
- [Java](http://java.oracle.com/)

## ⚠ Warnings
- Behavior tag
  - Every input tag  must have an unique id
- Structure tag
  - Onclick property only in buttons
   
## 🚩 Changelog
Details about each version are documented in the [releases section](https://github.com/wniemiec-mobilex/ama-compiler/releases).

## 🗺 Project structure
![architecture](https://raw.githubusercontent.com/wniemiec-mobilex/ama-compiler/master/docs/images/design/architecture.jpg)

