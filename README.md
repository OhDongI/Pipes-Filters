# PNF 실습 과제 모음 (Pipe & Filter Framework 기반)

## 📌 프로젝트 개요
 본 프로젝트는 **김정호 교수님의 클라이언트-서버와 프로그래밍 멘토링 수업**에서 제안된 실습 과제로, 이 레포지토리는 **Pipe & Filter Framework**를 활용한 실습 과제를 모아둔 프로젝트입니다. 각 과제는 분산 시스템 설계와 데이터 처리 방식을 학습하기 위해 단계적으로 구성되었으며, 과제별로 코드와 실행 결과를 포함하고 있습니다.  

---

## 🗂️ 레포지토리 구조
1. **PNF_HW1**: Pipe & Filter 프레임워크의 기본 개념을 학습하기 위한 초기 구현.
2. **PNF_HW2**: 중간 필터(Middle Filter)를 추가하여 데이터 흐름을 확장.
3. **PNF_HW3**: 다중 소스(Source) 및 싱크(Sink) 필터를 포함한 고급 데이터 처리 구현.
4. **PNF_HW4**: 복잡한 데이터 흐름 설계 시도 (현재 정상적으로 실행되지 않음).

---

## 📚 과제 설명
### 1. PNF_HW1
- **목표**: Pipe & Filter의 기본 구조와 흐름을 이해.
- **구현 내용**:
  - Source → Middle → Sink 필터의 단일 데이터 흐름.
  - 데이터 입력과 출력의 기본 처리.
- **결과**: 정상적으로 실행되며, Pipe & Filter의 구조와 작동 방식을 시각적으로 확인 가능.

### 2. PNF_HW2
- **목표**: 중간 필터(Middle Filter)를 활용한 데이터 처리 확장.
- **구현 내용**:
  - 필터 간 데이터 변환 추가.
  - 데이터 흐름에서 변환 및 필터링 로직 강화.
- **결과**: 성공적으로 실행되며, 복잡한 데이터 변환의 기초를 학습.

### 3. PNF_HW3
- **목표**: 다중 입력/출력 흐름 설계.
- **구현 내용**:
  - 다중 Source 및 Sink 필터 구현.
  - 데이터의 병렬 처리와 동시성 학습.
- **결과**: 정상적으로 실행되며, 다양한 데이터 흐름 설계 가능.

### 4. PNF_HW4
- **목표**: 고급 Pipe & Filter 설계를 통한 복잡한 데이터 흐름 구현.
- **구현 내용**:
  - 다중 Source와 Sink 사이의 복잡한 데이터 변환 로직 추가.
  - 에러 핸들링 및 동시성 제어 실험.
- **결과**: **현재 정상적으로 실행되지 않음.** 설계 및 코드 오류로 인해 개선 작업이 필요.

---
**본 프로젝트는 클라이언트-서버 모델의 학습과 함께, Pipe & Filter 및 Event-Bus 설계 패턴과의 비교를 통해 보다 심도 있는 소프트웨어 개발 경험을 제공하는 것을 목표로 합니다.**
