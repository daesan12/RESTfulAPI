<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<body>

    <section>
        <br>
        <div align="center">
            <h2>게시판 목록보기</h2>
        </div>

        <div align="center">
            1. 페이지 사이즈: ${pageSize} <br>
            2. 전체 레코드 수: ${totalCount} <br>
            3. 총 페이지 수: ${totalPage} <br>
            4. 현재 페이지: ${currentPage} <br>
        </div>

        <div align="center">
            <table border="1">
                <tr>
                    <td>순서</td>
                    <td>번호</td>
                    <td>이름</td>
                    <td>메모</td>
                    <td>나이</td>
                    <td>날짜</td>
                </tr>
                <c:forEach var="m" items="${li}" varStatus="iterStat" end=10>
                    <tr>
                        <td>${m.i + 1}</td>
                        <td>${m.idx}</td>
                        <td>${m.name}</td>
                        <td>${m.memo}</td>
                        <td>${m.age}</td>
                        <td>${m.regdate}</td>
                    </tr>
                </c:forEach >
            </table>
        </div>

        <div align="center">
            <c:choose>
                <c:when test="${currentPage > 1}">
                    <a href="/list?start=${start - pageSize}&pageSize=${pageSize}">이전</a>
                </c:when>
                <c:otherwise>
                    <span>이전</span>
                </c:otherwise>
            </c:choose>

            <c:forEach var="i" begin="1" end="${totalPage}">
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <strong>${i}</strong>
                    </c:when>
                    <c:otherwise>
                        <a href="/list?start=${(i - 1) * pageSize}&pageSize=${pageSize}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${currentPage < totalPage}">
                    <a href="/list?start=${start + pageSize}&pageSize=${pageSize}">다음</a>
                </c:when>
                <c:otherwise>
                    <span>다음</span>
                </c:otherwise>
            </c:choose>
        </div>
        
    </section>

</body>
</html>