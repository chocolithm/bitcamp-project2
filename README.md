# bitcamp-project2
- 주제 : To-Do 리스트
  팀원 : 이선아, 강윤상

추가할 기능?
  - 태그를 활용해서 다른사람 일정 검색?
  - 반복일정 등록
  - 가까운 일정 알림


Package util
  ## Class Prompt

Package vo
  ## Class User
  String name;
  String password;
  Date joinDate;
  LinkedList planList;

  ## Class Plan
  int no;
  String title;
  Date startDate;
  Date dueDate;
  String repeatedDays;

Package controller
  ## Class UserController
  LinkedList userList;
  LinkedList planList;
  addUser()
  listUser()
  updateUser()
  deleteUser()
  addPlan()
  getUserList()

  ## Class PlanController
  static User loginUser;
  LinkedList planList;
  addPlan()
  listPlan()
  viewPlan()
  updatePlan()
  deletePlan()
  getPlanListByUser()
  getAvailableDates()


--- 화면예시 ---

1. 사용자 관리
  관리자 체크
  1) 사용자 등록
  2) 사용자 목록
  3) 사용자 수정
  4) 사용자 삭제
  9) 이전

2. 일정 관리
  로그인 체크
  1) 일정 등록
  2) 일정 목록
  3) 일정 수정
  4) 일정 삭제
  9) 로그아웃

3. 약속잡기
  대상자 입력
  찾을 기간
  빈 시간 계산 (... 로딩화면)
  목록 나열

4. 종료

---------------------------------------------------------------------
[야!먹자]
[1] 로그인    [2] 회원가입
메인>

메인> 1
## [login]
ID? 선아
PW? 0000
로그인 되었습니다.

메인> 2
[회원가입]
ID : 윤상
PW : 0000
등록되었습니다.



# 메인메뉴
---------------------------------------
	<<야이날에먹자>>
	-7.10-7.12(선아, 윤상)
	-7.13(지영, 지윤, 미지)
---------------------------------------
[1] 내 일정      [2] 약속 추가      [3] 사용자관리     [0] 종료

/*************************************************************/
선아: login 화면, App.java에 sys.out.prinln(id, pw), TUI
윤상: APP.java (final int ans =3) ->user control
/*************************************************************/
메인>


# 일정관리
메인> 1

---------------------------------------------------------------------
No   : Title                           : Date
1.
2.
---------------------------------------------------------------------

[1] 등록    [2] 수정    [3] 삭제  [0] 이전


## 등록
메인/일정관리> 1

[등록]
제목 : 미니프로젝트2
월? 7
7월
월  화  수  목  금  토  일
1   2   3   4   5    6   7
.
.
.
일?(1~31 매주 월수금)) 1~31 월수금
등록되었습니다.

메인/일정관리>
---------------------------------------------------------------------
No   : Title                           : Date
1.    미니프로젝트2                      07.01-07.31 월수금
2.    미니프로젝트3                      07.23
---------------------------------------------------------------------

[1] 등록    [2] 수정    [3] 삭제   [0] 이전  


## 일정 수정
메인/일정관리> 3

[일정 수정]
수정할 일정 : 1

1) 제목
2) 기간
수정할 항목 : 1
'미니프로젝트2' 제목 변경 : 미니프로젝트
수정할 항목 : 2
월?) 7월
7월
월  화  수  목  금  토  일
1   2   3   4   5    6   7
.
.
.
일?(1~31 매주 월수금)) 1~31 월수금
수정되었습니다.

메인/일정관리>


## 일정 삭제
메인/일정관리> 4

[일정 삭제]
삭제할 일정 : 1
'미니프로젝트2' 일정을 삭제하시겠습니까? (y/n) : y
'미니프로젝트2' 일정을 삭제했습니다.

메인/일정관리>





# 약속잡기
메인> 2

[약속잡기]
함께할 사람(0: 다음) : 선아
'선아' 님을 추가합니다.
함께할 사람(0: 다음) : 윤상
'윤상' 님을 추가합니다.
함께할 사람(0: 다음) : 윤상
'윤상' 님이 이미 있습니다.
함께할 사람(0: 다음) : 0

검색할 기간(월) : 7

[가능한 일정 검색중 ...]

순위      기간
1         07-06 ~ 07-13
2         07-15 ~ 07-18
...

일정을 등록하시겠습니까?(y/n) y
제목 : 미니프로젝트3
월?) 7월

----------------------------------
7월
월  화  수  목  금  토  일
1   2   3   4   5    6   7
.
.
.
----------------------------------
일?(1-31 매주 월수금)) 5~7

'윤상', '선아' 님 앞으로
'미니프로젝트3' 일정이 등록되었습니다.

메인>





# 사용자관리
메인> 3
관리자 비밀번호 : 1111
비밀번호가 잘못되었습니다.

관리자 비밀번호 : 0000
[사용자관리 화면에 접속합니다.]

---------------------------------------------------------------------
No   : Name 
1.     선아                 
2.     윤상
---------------------------------------------------------------------

[1] 수정    [2] 삭제    [0] 이전


## 사용자 수정
메인/사용자관리> 3

[사용자 수정]
사용자 번호?> 2
1) 이름
2) 비밀번호
수정할 항목 : 1
test01 이름 변경 : <<이름 입력>>
수정할 항목 : 2
test01 비밀번호 변경 : <<비밀번호 입력>>

수정되었습니다.

메인/사용자관리>


## 사용자 삭제
메인/사용자관리> 4

[사용자 삭제]
사용자 번호?> 2
윤상 님을 삭제하시겠습니까?(y/n) : Y
윤상 님을 삭제했습니다.

메인/사용자관리>
