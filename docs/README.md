# MathQuestionHelper
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9d1f3b2e3bad43bdad604571a367203c)](https://app.codacy.com/manual/wsk-n-001/MathQuestion?utm_source=github.com&utm_medium=referral&utm_content=wsk-n-001/MathQuestion&utm_campaign=Badge_Grade_Dashboard)
[![Join the chat at https://gitter.im/MathQuestionHelper/community](https://badges.gitter.im/MathQuestionHelper/community.svg)](https://gitter.im/MathQuestionHelper/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://travis-ci.org/wsk-n-001/MathQuestion.svg?branch=master)](https://travis-ci.org/wsk-n-001/MathQuestion)
这是一个用于帮助你解决数学问题的程序  
你只需要运行它，并在文本框里编写你的Java代码即可，无需IDE  
## 各区域作用
Import编写区：写import语句  
主方法编写区：写你的主要程序，无需声明类和方法，直接编写代码  
其他方法编写区：编写你的程序的其他部分，需要方法声明  
## 工作原理
本程序会将你写的代码添加进一个默认框架内：  
import代码  
类声明  
&emsp;主方法  
&emsp;&emsp;new Main.go();  
&emsp;&emsp;你编写的主方法  
&emsp;&emsp;&emsp;语句  
&emsp;其他方法  
&emsp;&emsp;&emsp;语句  
结束
## 源代码
见github仓库  
jdk:openjdk11  
需要openjfx14的库来运行（库已在jar里包含）
