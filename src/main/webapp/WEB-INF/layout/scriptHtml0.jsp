<%--
  Created by IntelliJ IDEA.
  User: kdragonk18
  Date: 04/10/2023
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- -------- END FOOTER 3 w/ COMPANY DESCRIPTION WITH LINKS & SOCIAL ICONS & COPYRIGHT ------- -->
<!--   Core JS Files   -->
<script src="/soft-ui-dashboard-main/assets/js/core/popper.min.js"></script>
<script src="/soft-ui-dashboard-main/assets/js/core/bootstrap.min.js"></script>
<script src="/soft-ui-dashboard-main/assets/js/plugins/perfect-scrollbar.min.js"></script>
<script src="/soft-ui-dashboard-main/assets/js/plugins/smooth-scrollbar.min.js"></script>
<script>
    var win = navigator.platform.indexOf('Win') > -1;
    if (win && document.querySelector('#sidenav-scrollbar')) {
        var options = {
            damping: '0.5'
        }
        Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
    }
</script>
<!-- Github buttons -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
<!-- Control Center for Soft Dashboard: parallax effects, scripts for the example pages etc -->
<script src="/soft-ui-dashboard-main/assets/js/soft-ui-dashboard.min.js?v=1.0.7"></script>
