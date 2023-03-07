# java-blackjack

블랙잭 미션 저장소

# 기능 목록

- 그림
    - 스페이드, 클로버, 하트, 다이아 네 가지 그림이 있다.
- 문자
    - A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K
    - 문자에 따른 점수를 알 수 있다
    - A인지 알 수 있다.
- 카드
    - 점수를 알 수 있다.
    - 문자가 A인지 알 수 있다.
- 덱
    - 52장의 카드로 덱 하나를 구성한다.
    - 랜덤하게 카드를 한 장 뽑는다.
    - 카드가 바닥나면 예외를 던진다.
- 패
    - 카드를 추가할 수 있다.
    - 점수를 계산한다.
        - A는 플레이어에게 유리하게 1, 11로 계산한다.
    - 패와 패를 비교해 승패를 알아낼 수 있다.
    - 패가 `Bust`인지 알 수 있다.
- 유저
    - 이름을 가진다.
    - 덱에서 카드를 뽑을 수 있다.
    - 점수가 21 미만이면 카드를 더 뽑을 수 있다 `Hit`
    - 점수가 21 이상이면 카드를 더 이상 뽑을 수 없다 `Stand`
- 딜러
    - 이름을 가진다.
    - 덱에서 카드를 뽑을 수 있다.
    - 점수가 17 미만이면 카드를 더 뽑을 수 있다 `Hit`
    - 점수가 17 이상이면 카드를 더 이상 뽑을 수 없다 `Stand`
- 게임
    - 패를 2번 돌린다.
    - 플레이어에게 카드를 줄 수 있다.
    - 플레이어의 승패를 알 수 있다.
        - 딜러의 승패는 플레이어의 승패로 알아낸다.
- 승패
    - 승,무,패가 있다.
    - 뒤집을 수 있다.

### 어플리케이션

- 참가자를 받아 게임을 시작한다.
    - 카드를 1장씩 2번 돌린다.
    - 현재 카드 상태를 보여준다.
- 게임을 진행한다.
    - 유저들의 `Hit` 이나 `Stand` 선택을 받아 카드를 추가로 주거나, 주지 않는다.
        - 유저가 `Hit` 할 수 없다면 묻지 않는다.
    - 딜러가 `Hit` 가능하면, 카드를 추가로 준다.
        - 몇 번 `Hit` 했는지 센다.
- 게임 결과를 출력한다.

### 입력

- [x] 게임에 참여할 사람의 이름을 입력 받는다.
- [x] 플레이어에게 카드를 한 장 더 받을 지 물어본다.

### 출력

- [x] 카드를 몇 장씩 나눴는지 출력한다.
- [x] 어떤 카드를 받았는지 출력한다.
- [x] 딜러가 추가로 받은 카드를 출력한다.
- [x] 딜러의 `Hit` 횟수나 `Stand` 정보를 출력한다.
- [x] 전체 참여자의 카드와 점수를 출력한다.
- [x] 딜러의 모든 승패를 출력한다
- [x] 전체 참여자의 승패를 출력한다.

<details>
<summary><h2>리팩터링 목록</h2></summary>

- [x] Game::getResult 메서드 너무 복잡하고 길어
    - [x] index -> name 이용하도록 변경 (isWon, dealAnotherCard)

- [x] Application 메서드 분리
- [x] Game도 인스턴스 변수 개수 3개다

- [x] Player::calculateScore 메서드 분리
- [x] Game::dealCards indent가 2 임.
- [x] Player 인스턴스 변수 개수 3개다.
- [x] 카드 글자 리팩터링 Deck::buildCards()
- [x] Participants 일급 컬렉션 사용
- [x] 플레이어와 유저, 딜러 관계 정리
    - 유저와 딜러는 모두 플레이어다.
    - 유저가 필요한 곳에 딜러가 들어갈 수 없도록 매개변수 등을 확실하게 한다.

- [x] 테스트 픽스처 생성 메서드
- [ ] 매직 넘버 상수화
- [x] Cards 구현
- [ ] 제약조건을 더 고려해보자
    - 인원수 제한 (카드는 52개임)
    - `Busted`된 플레이어는 카드를 받으면 안된다.
    - 입력값 검증
- [ ] 리팩터링된 구조에 맞게 테스트 정리
    - 리팩터링 할 때 테스트는 어떻게 해야할까?
        - 이미 테스트 되는 부분이긴 하다.
        - 하지만 다른 클래스로 분리된다.
        - 지금처럼 따로? 아니면 미리 복사해둘까?
- [ ] 옵저버 패턴 테스트 해보기

</details>

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
