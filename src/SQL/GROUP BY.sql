-- 자동차 대여 기록에서 대여중 / 대여 가능 여부 구분하기
문제 설명
다음은 어느 자동차 대여 회사의 자동차 대여 기록 정보를 담은 CAR_RENTAL_COMPANY_RENTAL_HISTORY 테이블입니다. CAR_RENTAL_COMPANY_RENTAL_HISTORY 테이블은 아래와 같은 구조로 되어있으며, HISTORY_ID, CAR_ID, START_DATE, END_DATE 는 각각 자동차 대여 기록 ID, 자동차 ID, 대여 시작일, 대여 종료일을 나타냅니다.

Column name	Type	Nullable
HISTORY_ID	INTEGER	FALSE
CAR_ID	INTEGER	FALSE
START_DATE	DATE	FALSE
END_DATE	DATE	FALSE
문제
CAR_RENTAL_COMPANY_RENTAL_HISTORY 테이블에서 2022년 10월 16일에 대여 중인 자동차인 경우 '대여중' 이라고 표시하고, 대여 중이지 않은 자동차인 경우 '대여 가능'을 표시하는 컬럼(컬럼명: AVAILABILITY)을 추가하여 자동차 ID와 AVAILABILITY 리스트를 출력하는 SQL문을 작성해주세요. 이때 반납 날짜가 2022년 10월 16일인 경우에도 '대여중'으로 표시해주시고 결과는 자동차 ID를 기준으로 내림차순 정렬해주세요.

예시
예를 들어 CAR_RENTAL_COMPANY_RENTAL_HISTORY 테이블이 다음과 같다면

HISTORY_ID	CAR_ID	START_DATE	END_DATE
1	4	2022-09-27	2022-09-27
2	3	2022-10-03	2022-10-04
3	2	2022-10-05	2022-10-05
4	1	2022-10-11	2022-10-16
5	3	2022-10-13	2022-10-15
6	2	2022-10-15	2022-10-17
2022년 10월 16일에 대여 중인 자동차는 자동차 ID가 1, 2인 자동차이고, 대여 가능한 자동차는 자동차 ID가 3, 4이므로, '대여중' 또는 '대여 가능' 을 표시하는 컬럼을 추가하고, 자동차 ID를 기준으로 내림차순 정렬하면 다음과 같이 나와야 합니다.

CAR_ID	AVAILABILITY
4	대여 가능
3	대여 가능
2	대여중
1	대여중

-- 답안
SELECT
    CAR_ID,
    CASE WHEN CAR_ID IN (
        SELECT CAR_ID
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE START_DATE <= '2022-10-16' AND END_DATE >= '2022-10-16'
    ) THEN '대여중'
         ELSE '대여 가능'
        END AS AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY CAR_ID
ORDER BY CAR_ID DESC


-- 저자 별 카테고리 별 매출액 집계하기
문제 설명
다음은 어느 한 서점에서 판매중인 도서들의 도서 정보(BOOK), 저자 정보(AUTHOR) 테이블입니다.

BOOK 테이블은 각 도서의 정보를 담은 테이블로 아래와 같은 구조로 되어있습니다.

Column name	Type	Nullable	Description
BOOK_ID	INTEGER	FALSE	도서 ID
CATEGORY	VARCHAR(N)	FALSE	카테고리 (경제, 인문, 소설, 생활, 기술)
AUTHOR_ID	INTEGER	FALSE	저자 ID
PRICE	INTEGER	FALSE	판매가 (원)
PUBLISHED_DATE	DATE	FALSE	출판일
AUTHOR 테이블은 도서의 저자의 정보를 담은 테이블로 아래와 같은 구조로 되어있습니다.

Column name	Type	Nullable	Description
AUTHOR_ID	INTEGER	FALSE	저자 ID
AUTHOR_NAME	VARCHAR(N)	FALSE	저자명
BOOK_SALES 테이블은 각 도서의 날짜 별 판매량 정보를 담은 테이블로 아래와 같은 구조로 되어있습니다.

Column name	Type	Nullable	Description
BOOK_ID	INTEGER	FALSE	도서 ID
SALES_DATE	DATE	FALSE	판매일
SALES	INTEGER	FALSE	판매량
문제
2022년 1월의 도서 판매 데이터를 기준으로 저자 별, 카테고리 별 매출액(TOTAL_SALES = 판매량 * 판매가) 을 구하여, 저자 ID(AUTHOR_ID), 저자명(AUTHOR_NAME), 카테고리(CATEGORY), 매출액(SALES) 리스트를 출력하는 SQL문을 작성해주세요.
결과는 저자 ID를 오름차순으로, 저자 ID가 같다면 카테고리를 내림차순 정렬해주세요.

예시
예를 들어 BOOK 테이블과 AUTHOR 테이블, BOOK_SALES 테이블이 다음과 같다면

BOOK_ID	CATEGORY	AUTHOR_ID	PRICE	PUBLISHED_DATE
1	인문	1	10000	2020-01-01
2	경제	1	9000	2021-02-05
3	경제	2	9000	2021-03-11
AUTHOR_ID	AUTHOR_NAME
1	홍길동
2	김영호
BOOK_ID	SALES_DATE	SALES
1	2022-01-01	2
2	2022-01-02	3
1	2022-01-05	1
2	2022-01-20	5
2	2022-01-21	6
3	2022-01-22	2
2	2022-02-11	3
2022년 1월의 도서 별 총 매출액은 도서 ID 가 1 인 도서가 총 3권 * 10,000원 = 30,000원, 도서 ID 가 2 인 도서가 총 14권 * 9,000 = 126,000원 이고, 도서 ID 가 3 인 도서가 총 2권 * 9,000 = 18,000원 입니다.

저자 별 카테고리 별로 매출액을 집계하면 결과는 다음과 같습니다.

AUTHOR_ID	AUTHOR_NAME	CATEGORY	TOTAL_SALES
1	홍길동	인문	30000
1	홍길동	경제	126000
2	김영호	경제	18000
그리고 저자 ID, 카테고리 순으로 내림차순 정렬하면 다음과 같이 나와야 합니다.

AUTHOR_ID	AUTHOR_NAME	CATEGORY	TOTAL_SALES
1	홍길동	인문	30000
1	홍길동	경제	126000
2	김영호	경제	18000

-- 답안
SELECT
    A.AUTHOR_ID,
    A.AUTHOR_NAME,
    B.CATEGORY,
    SUM(BS.SALES * B.PRICE) AS TOTAL_SALES
FROM BOOK B
         JOIN AUTHOR A ON B.AUTHOR_ID=A.AUTHOR_ID
         JOIN BOOK_SALES BS ON B.BOOK_ID=BS.BOOK_ID
WHERE MONTH(BS.SALES_DATE) = 1
GROUP BY A.AUTHOR_ID, B.CATEGORY
ORDER BY A.AUTHOR_ID, B.CATEGORY DESC

