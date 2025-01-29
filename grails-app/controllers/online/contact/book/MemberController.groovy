package online.contact.book

import org.apache.commons.collections.ListUtils

class MemberController {

    MemberService memberService

    List<Member> methodA(def ll) {
        return Member.createCriteria().list {
            or { ll.collate(999).each { 'in'('id', it) } }
        }
    }

    def details(Integer id) {
        def response = memberService.getById(id)
        if (!response) {
            redirect(controller: "member", action: "index")
        } else {
            [member: response]
        }
    }

    def create() {
        [member: flash.redirectParams]
    }

    def list() {
        [list: Member.findAllById(1)]
    }

    def save() {
        def response = memberService.save(params)
        if (!response.isSuccess) {
            flash.redirectParams = response.model
            redirect(controller: "member", action: "create")
        } else {
            redirect(controller: "member", action: "index")
        }
    }

    def edit(Integer id) {
        if (flash.redirectParams) {
            [member: flash.redirectParams]
        } else {
            def response = memberService.getById(id)
            if (!response) {
                redirect(controller: "member", action: "index")
            } else {
                [member: response]
            }
        }
    }

    def update() {
        def response = memberService.getById(params.id)
        if (!response) {
            redirect(controller: "member", action: "index")
        } else {
            response = memberService.update(response, params)
            if (!response.isSuccess) {
                flash.redirectParams = response.model
                redirect(controller: "member", action: "edit")
            } else {
                redirect(controller: "member", action: "index")
            }
        }
    }


    def index() {
        def response = memberService.list(params)
        [memberList: response.list, total: response.count]
    }

    private void aMethod() {
        def members = 1..2000
        List<Member> result = new ArrayList<Member>();
        final List<List<Integer>> partitions = members.collate(999);
        for (List<Integer> partition : partitions) {
            result.addAll(Member.findAllByIdInList(partition))
        }
        System.out.println("members 1 ================" + result)
        def ll = 1..2000
        def r2 = methodA(ll)
        System.out.println("members 2 ================" + r2)

        [list: result]
    }

    def delete(Integer id) {
        def response = memberService.getById(id)
        if (!response) {
            redirect(controller: "member", action: "index")
        } else {
            response = memberService.delete(response)
            redirect(controller: "member", action: "index")
        }
    }
}