
# Java Deque & RandomizedQueue

> **English | 中文說明請往下看 👇**

---

## English

### Overview

This repository implements three classic data structures and a client in Java:

- **Deque** (`Deque.java`): Double-ended queue supporting constant-time insertion and removal at both ends.
- **RandomizedQueue** (`RandomizedQueue.java`): A queue where the removed item is chosen uniformly at random.
- **Permutation** (`Permutation.java`): Reads a sequence of strings and prints a random k-permutation using RandomizedQueue.

All code is written in pure Java (no external dependencies), and passes all official tests (correctness, timing, memory) for Princeton COS226 Algorithms course.

---

### Features

- Fully tested for correctness, timing, and memory efficiency
- Implements Java iterator interfaces
- Clear, idiomatic, well-commented code

---

### File Overview

| File                   | Description                                             |
|------------------------|--------------------------------------------------------|
| `Deque.java`           | Double-ended queue (generic, iterable)                 |
| `RandomizedQueue.java` | Random-removal queue (resizing array, iterable)        |
| `Permutation.java`     | Command-line client for k-permutation sampling         |

---

### How to Compile & Run

```sh
javac Deque.java
javac RandomizedQueue.java
javac Permutation.java
java Permutation 3 < distinct.txt
```

- Replace `3` with your desired k.
- Replace `distinct.txt` with your input file.

---

### Test Report

- 100% correctness (all test cases passed)
- High performance (timing and memory efficient)
- Passes all style checks (except minor warnings)

---

### License

MIT

---

### Author

洪禎 (Chen-Hong)  
Graduate student, National Chung Cheng University  
For academic and learning purposes

---

## 中文說明

### 專案簡介

本專案以 Java 實作三種經典資料結構及用戶端：

- **Deque（雙端佇列）**：支援首尾 O(1) 插入刪除。
- **RandomizedQueue（隨機佇列）**：刪除時隨機選取元素。
- **Permutation（隨機抽取）**：讀取字串序列，隨機印出 k 個不重複元素。

所有程式碼均符合 Princeton COS226 演算法課程規範，並通過所有正確性、效能、記憶體測試。

---

### 特色

- 完整通過所有測試（正確性、效能、記憶體）
- 實作 Java 迭代器介面，支援 for-each
- 註解齊全，風格清晰易懂

---

### 檔案說明

| 檔案                    | 說明                                 |
|-------------------------|--------------------------------------|
| `Deque.java`            | 雙端佇列（泛型，支援迭代器）         |
| `RandomizedQueue.java`  | 隨機佇列（可調整陣列，支援迭代器）   |
| `Permutation.java`      | 指令列用戶端，隨機抽樣 k 個不重複元素|

---

### 編譯與執行

```sh
javac Deque.java
javac RandomizedQueue.java
javac Permutation.java
java Permutation 3 < distinct.txt
```

- `3` 為欲抽取的 k 值
- `distinct.txt` 為你的輸入檔案

---

### 測試報告

- 通過所有官方測資（正確性、效能、記憶體）
- 符合程式碼風格規範（僅小部分警告）

---

### 授權

MIT

---

### 作者

洪禎（Chen-Hong）  
國立中正大學電訊傳播研究所碩士生  
僅供學術與學習用途

---

歡迎 star、fork、交流指教！
