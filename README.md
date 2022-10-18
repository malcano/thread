[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=8934841&assignment_repo_type=AssignmentRepo)
# 6_supermarket

## 제출 기한

* 10/23 (일) 23:59

## 과제 목표

- 아래 요구사항들을 만족하는 멀티스레딩 프로그램 `Supermarket.java` 을 구현한다.
- `Supermarket.java` 는 `src` 디렉토리에 있다.

## 요구 사항

1. Supermarket.java

- `n`명의 계산원이 손님들 `customers`을 응대하는 메서드 `serveCustomer(int[] customers, int n)`를 구현한다.
- `customers`의 각 int 원소는 손님 1명을 의미하며, 값은 처리해야하는 물품의 갯수이다.
- 손님들은 FIFO (First-In-First-Out) 구조의 큐(`Queue`)를 이용하여 순차적으로 응대된다.
- 계산원은 `Cashier` 클래스로 모델링되며 `n`명의 계산원이 존재한다. 
- 손님을 응대하고 있지 않은 계산원이 큐에서 손님을 1명씩 응대하여 물품을 처리하며, 이 행위는 `CashierTask`라는 스레드 클래스로 모델링한다.
- `serveCustomer()`에는 밀리초 단위로 시간을 측정하는 로직이 구현되어 있으며, `n`명의 계산원이 주어진 손님들을 동시에 응대할때 소요되는 시간을 계산한다.
- **변수, 배열 초기화 등의 로직을 제외하고 계산원이 손님을 응대하는 작업에 대해서만 구현한 코드가 시간 측정란에 있어야함에 유의한다.**
- `serveCustomer()`는 측정된 밀리초 시간에 100을 나눈 후 리턴한다.
- 계산원이 물품을 1개 처리할 때의 소요 시간은 100ms로 가정한다.
- 예를 들어,
    - customers = {5, 3, 4}, n = 1 일 때, 총 1200ms가 걸리고 12를 리턴한다.(계산원 1명이 모든 손님들의 물품을 처리해야 하므로)
    - customers = {10, 2, 3, 3}, n = 2 일 때, 총 1000ms가 걸리고 10을 리턴한다.(첫번째 계산원이 10개를 처리하는 동안 두번째 계산원이 나머지를 모두 응대)
    - customers = {2, 3, 10}, n = 2 일 때, 총 1200m가 걸리고 12를 리턴한다.(첫번째 계산원이 2, 10 처리, 두번째 계산원이 3 처리)
- 물건 처리 시 실제 소요시간을 발생시키기 위해 `Thread.sleep(int milliseconds)` 메서드를 이용한다.

## 주의 사항

- 로컬에서 여러번 테스트 후 GitHub에 제출하길 바랍니다.
- 무한 루프, 다수 스레드 생성 등 원격 머신의 자원을 많이 잡아먹는 코드 제출은 금지합니다.
- `Queue`는 직접 구현합니다.(컬렉션 프레임워크의 Queue 사용 금지)
- `Thread` 클래스 또는 `Runnable` 클래스만 사용하여 구현합니다.(Java에서 지원하는 멀티스레딩 프레임워크 사용금지)
- **스레드를 안쓰고 구현 시 0점입니다.**

## 컴파일 및 테스트 방법

- 컴파일 방법
    - `./build.sh [소스파일명]` (예: `./build.sh Supermarket.java`)
- 테스트 방법
    - `./run.sh [대상클래스명]` (예: `./run.sh Supermarket`)
- 쉘 스크립트 실행은 저장소 디렉토리에서 실행해야합니다.(src 디렉토리 내부가 아닙니다!)
- 테스트 소스 파일은 test 디렉토리에 있습니다.
- VSCode를 이용하는 학생들은 클라썸에 공지한 방법으로 컴파일 및 테스트를 진행할 수 있습니다.

## 제출 및 주석달기

* git 명령어를 이용해서 로컬 저장소로 push 하거나, GitHub 저장소에서 직접 편집하여 제출할 수도 있습니다.
* 코드에 주석을 간략히 달아서 어떻게 문제를 해결했는지 설명합니다.