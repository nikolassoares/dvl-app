package com.dvlcube.app.rest;

import com.dvlcube.app.interfaces.MenuItem;
import com.dvlcube.app.jpa.repo.JobRepository;
import com.dvlcube.app.manager.data.JobBean;
import com.dvlcube.app.manager.data.SkillBean;
import com.dvlcube.app.manager.data.vo.MxRestResponse;
import com.dvlcube.utils.interfaces.rest.MxFilterableBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.dvlcube.app.manager.data.e.Menu.CONFIGURATION;

@RestController
@MenuItem(value = CONFIGURATION)
@RequestMapping("${dvl.rest.prefix}/jobs")
public class JobService implements MxFilterableBeanService<JobBean, Long> {

	@Autowired
	private JobRepository jobRepository;

	@Override
	@GetMapping
	public Iterable<JobBean> get(@RequestParam Map<String, String> params) {
		return jobRepository.firstPage();
	}

	@Override
	@GetMapping("/{id}")
	public Optional<JobBean> get(@PathVariable Long id) {
		return jobRepository.findById(id);
	}

	@Override
	@PostMapping
	public MxRestResponse post(@Valid @RequestBody JobBean body) {
		JobBean save = jobRepository.save(body);
		return GenericRestResponse.ok(save.getId());
	}

	@GetMapping("/filtered")
	public List<JobBean> getFiltered(@RequestParam Map<String, String> params) {
		return jobRepository.findAllBy(params);
	}

	@GetMapping("/group/{group}/filtered")
	public List<JobBean> getGroupFiltered(@PathVariable String group, @RequestParam Map<String, String> params) {
		return jobRepository.findAllBy(params, group);
	}

	@GetMapping("/like")
	public Iterable<JobBean> getLike(@RequestParam(required = true) String id) {
		return jobRepository.findAllLike(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		jobRepository.deleteById(id);
	}
}
