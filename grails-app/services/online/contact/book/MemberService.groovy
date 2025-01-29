package online.contact.book

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class MemberService {
    def save(GrailsParameterMap params) {
           Member member = new Member(params)
        def response
//        for (int i = 1005; i < 2000; i++) {
//            Member member = new Member(firstName: params.firstName + "_" + i, lastName: params.lastName + "_" + i, email: "aaa" + i + "@google.com", password: params.password)
            response = AppUtil.saveResponse(false, member)
            // if (member.validate()) {
            member.save(flush: true)

            // if (!member.hasErrors()) {
            // response.isSuccess = true
            //}
//        }
        response.isSuccess = true
        return response

    }


    def update(Member member, GrailsParameterMap params) {
        member.properties = params
        def response = AppUtil.saveResponse(false, member)
        if (member.validate()) {
            member.save(flush: true)
            if (!member.hasErrors()) {
                response.isSuccess = true
            }
        }
        return response
    }


    def getById(Serializable id) {
        return Member.get(id)
    }


    def list(GrailsParameterMap params) {
        params.max = params.max ?: GlobalConfig.itemsPerPage()
        //List<Member> memberList = Member.createCriteria().list(params) {
        List<Member> memberList = Member.createCriteria().list() {
            if (params?.colName && params?.colValue) {
                like(params.colName, "%" + params.colValue + "%")
            }
            if (!params.sort) {
                order("id", "desc")
            }
        }
        return [list: memberList, count: Member.count()]
    }


    def delete(Member member) {
        try {
            member.delete(flush: true)
        } catch (Exception e) {
            println(e.getMessage())
            return false
        }
        return true
    }
}
