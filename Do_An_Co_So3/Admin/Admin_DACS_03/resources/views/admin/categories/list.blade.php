@extends("admin.main")

@section('contents')
<table class="table">
    <thead>
      <tr>
        <th scope="col" class="text-center">STT</th>
        <th scope="col " class="text-center">&nbsp;</th>
        <th scope="col " class="text-center">Tên</th>
        <th scope="col" class="text-center">Trạng thái</th>
        <th scope="col" class="text-center"><th style="width:100px">&nbsp;</th></th>
      </tr>
    </thead>
    <tbody>
        @foreach ($categoies as $key  => $category )
            <tr>
                <th scope="row" class="align-middle text-center">{{$key+=1}}</th>
                <td class="text-center">
                    <img src="/storage/{{$category->image}}" alt="" style="width:80px">
                </td>
                <td class="align-middle text-center">
                    {{$category->name}}
                </td>
                @if ($category->active == 1)
                    <td class="align-middle text-center">
                        Đang hoạt động
                    </td>
                    @else
                    <td class="text-black-50 align-middle text-center">
                        Không hoạt động
                    </td>
                @endif
                <td class="text-right">
                    <a class="btn btn-primary btn-sm" href="/admin/categories/edit/{{ $category->id }}">
                        <i class="fa-solid fa-pen-to-square"></i>
                    </a>
                    <a href="#" class="btn btn-danger btn-sm"
                        onclick = "removeRow({{ $category->id }},'/admin/categories/destroy')">
                        <i class="fa-solid fa-trash-can "></i>
                    </a>
                </td>
        </tr>
        @endforeach
    </tbody>
  </table>
@endsection
