@extends("admin.main")

@section('contents')
<table class="table">
    <thead>
      <tr>
        <th scope="col" class="text-center">STT</th>
        <th scope="col " class="text-center">&nbsp;</th>
        <th scope="col " class="text-center">Tên</th>
        <th scope="col" class="text-center">Menu</th>
        <th scope="col" class="text-center">Thể loại</th>
        <th scope="col" class="text-center">Trạng thái</th>
        <th scope="col" class="text-center">Đề xuất</th>
        <th scope="col" class="text-center">Lượt thích</th>
        <th scope="col" class="text-center"><th style="width:100px">&nbsp;</th></th>
      </tr>
    </thead>
    <tbody>
        @foreach ($mangas as $key  => $manga )
            <tr>
                <th scope="row" class="align-middle text-center">{{$key+=1}}</th>
                <td class="text-center">
                    <img src="/storage/{{$manga->image}}" alt="" style="width:80px">
                </td>
                <td class="align-middle text-center">
                    {{$manga->name}}
                </td>
                <td class="align-middle text-center">
                    {{$manga->menu->name}}
                </td>
                <td class="align-middle text-center">
                    {{$manga->category->name}}
                </td>
                @if ($manga->suggest == 1)
                    <td class="align-middle text-center">
                        Đề xuất
                    </td>
                @else
                    <td class="text-black-50 align-middle text-center">
                        Không đề xuất
                    </td>
                @endif
                @if ($manga->active == 1)
                    <td class="align-middle text-center">
                        Đang hoạt động
                    </td>
                    @else
                    <td class="text-black-50 align-middle text-center">
                        Không hoạt động
                    </td>
                @endif
                <td class="align-middle text-center">
                    {{$manga->number_like}}
                </td>
                <td class="text-right" colspan="2">
                    <a class="btn btn-success btn-sm" href="/admin/mangas/chapters/listNovel/{{ $manga->id }}">
                        <i class="fa-solid fa-chart-column"></i>
                    </a>
                    <a class="btn btn-primary btn-sm" href="/admin/mangas/edit/{{ $manga->id }}">
                        <i class="fa-solid fa-pen-to-square"></i>
                    </a>
                    <a href="#" class="btn btn-danger btn-sm"
                        onclick = "removeRow({{ $manga->id }},'/admin/mangas/destroy')">
                        <i class="fa-solid fa-trash-can "></i>
                    </a>
                </td>
        </tr>
        @endforeach
    </tbody>
  </table>
  {!! $mangas->links() !!}

@endsection
